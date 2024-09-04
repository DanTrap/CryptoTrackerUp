package ru.mobileup.template.features.coins.presentation.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import me.aartikov.replica.algebra.normal.withKey
import me.aartikov.replica.keyed.keepPreviousData
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

    private val coinsReplica = coinRepository.coins
        .keepPreviousData().withKey(selectedCurrency)

    override val coinsState = coinsReplica.observe(this, errorHandler)

    override fun onCurrencyClick(currency: Currency) {
        selectedCurrency.value = currency
    }

    override fun onCoinClick(coinId: String) {
        onOutput(CoinListComponent.Output.CoinDetailsRequested(coinId))
    }

    override fun onRetryClick() {
        coinsReplica.refresh()
    }

    override fun onRefresh() {
        coinsReplica.refresh()
    }

    @Serializable
    private data class PersistentState(
        val selectedCurrency: Currency,
    )
}