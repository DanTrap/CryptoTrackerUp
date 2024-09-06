package ru.mobileup.template.features.coins.presentation.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.serialization.Serializable
import me.aartikov.replica.algebra.normal.withKey
import me.aartikov.replica.algebra.paged.withKey
import me.aartikov.replica.keyed.keepPreviousData
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.componentScope
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.core.utils.persistent
import ru.mobileup.template.features.coins.data.CoinRepository
import ru.mobileup.template.features.coins.domain.CoinId
import ru.mobileup.template.features.coins.domain.Currency
import ru.mobileup.template.features.coins.presentation.search.RealCoinsSearchComponent

class RealCoinListComponent(
    componentContext: ComponentContext,
    private val onOutput: (CoinListComponent.Output) -> Unit,
    coinRepository: CoinRepository,
    errorHandler: ErrorHandler,
) : ComponentContext by componentContext, CoinListComponent {

    companion object {
        private const val DEBOUNCE_PERIOD_MS = 500L
    }

    override val searchComponent = RealCoinsSearchComponent(componentContext)

    // Придумал пока только такое решение c дополнительным debounced полем
    private val debouncedSearchState = MutableStateFlow("")

    private fun debounceSearchState() = searchComponent.searchState
        .debounce(DEBOUNCE_PERIOD_MS)
        .combine(searchComponent.isValidQueryState) { query, isValid ->
            if (isValid) debouncedSearchState.value = query
        }.launchIn(componentScope)

    init {
        persistent(
            serializer = PersistentState.serializer(),
            save = {
                PersistentState(
                    selectedCurrency = selectedCurrency.value
                )
            },
            restore = { state -> selectedCurrency.value = state.selectedCurrency }
        )
        debounceSearchState()
    }

    override val currencies = Currency.entries

    override val selectedCurrency = MutableStateFlow(currencies[0])

    private val coinsPagedReplica = coinRepository.coinsPagedReplica.withKey(selectedCurrency)

    override val coinsPagedState = coinsPagedReplica.observe(this, errorHandler)

    private val coinsSearchReplica = coinRepository.coinsSearchReplica
        .keepPreviousData().withKey(debouncedSearchState)

    override val coinsSearchState = coinsSearchReplica.observe(this, errorHandler)

    override fun onCurrencyClick(currency: Currency) {
        selectedCurrency.value = currency
    }

    override fun onCoinClick(coinId: CoinId) {
        onOutput(CoinListComponent.Output.CoinDetailsRequested(coinId))
    }

    override fun onRetryClick() {
        coinsPagedReplica.refresh()
    }

    override fun onRefresh() {
        coinsPagedReplica.refresh()
    }

    override fun onLoadNext() {
        coinsPagedReplica.loadNext()
    }

    @Serializable
    private data class PersistentState(
        val selectedCurrency: Currency,
    )
}