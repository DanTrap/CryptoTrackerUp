package ru.mobileup.template.features.coins.presentation.list

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.PagedState
import ru.mobileup.template.features.coins.domain.CoinId
import ru.mobileup.template.features.coins.domain.CoinSearch
import ru.mobileup.template.features.coins.domain.Currency
import ru.mobileup.template.features.coins.domain.PagedCoins
import ru.mobileup.template.features.coins.presentation.search.CoinsSearchComponent

interface CoinListComponent {

    val searchComponent: CoinsSearchComponent

    val currencies: List<Currency>

    val selectedCurrency: StateFlow<Currency>

    val coinsPagedState: StateFlow<PagedState<PagedCoins>>

    val coinsSearchState: StateFlow<LoadableState<List<CoinSearch>>>

    fun onCurrencyClick(currency: Currency)

    fun onCoinClick(coinId: CoinId)

    fun onRetryClick()

    fun onRefresh()

    fun onLoadNext()

    sealed interface Output {
        data class CoinDetailsRequested(val coinId: CoinId) : Output
    }
}