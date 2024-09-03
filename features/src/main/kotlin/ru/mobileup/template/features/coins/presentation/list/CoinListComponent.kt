package ru.mobileup.template.features.coins.presentation.list

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.Currency

interface CoinListComponent {

    val currencies: List<Currency>

    val selectedCurrency: StateFlow<Currency>

    val coinsState: StateFlow<LoadableState<List<Coin>>>

    fun onCurrencyClick(currency: Currency)

    fun onCoinClick(coinId: String)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class CoinDetailsRequested(val coinId: String) : Output
    }
}