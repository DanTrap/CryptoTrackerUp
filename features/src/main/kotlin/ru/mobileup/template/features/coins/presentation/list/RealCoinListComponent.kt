package ru.mobileup.template.features.coins.presentation.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import me.aartikov.replica.algebra.paged.withKey
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.core.utils.persistent
import ru.mobileup.template.features.coins.data.CoinRepository
import ru.mobileup.template.features.coins.domain.Currency

class RealCoinListComponent(
    componentContext: ComponentContext,
    private val onOutput: (CoinListComponent.Output) -> Unit,
    coinRepository: CoinRepository,
    errorHandler: ErrorHandler,
) : ComponentContext by componentContext, CoinListComponent {

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
    }

    override val currencies = Currency.entries

    override val selectedCurrency = MutableStateFlow(currencies[0])

    private val coinsPagedReplica = coinRepository.coinsPagedReplica.withKey(selectedCurrency)

    override val coinsPagedState = coinsPagedReplica.observe(this, errorHandler)

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