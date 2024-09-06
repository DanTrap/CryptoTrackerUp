package ru.mobileup.template.features.coins.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.PagedState
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.CoinId
import ru.mobileup.template.features.coins.domain.CoinSearch
import ru.mobileup.template.features.coins.domain.Currency
import ru.mobileup.template.features.coins.domain.PagedCoins
import ru.mobileup.template.features.coins.presentation.search.CoinsSearchComponent
import ru.mobileup.template.features.coins.presentation.search.FakeCoinsSearchComponent

class FakeCoinListComponent : CoinListComponent {

    override val coinsSearchState = MutableStateFlow(LoadableState(data = emptyList<CoinSearch>()))

    override val searchComponent: CoinsSearchComponent = FakeCoinsSearchComponent()

    override val currencies: List<Currency> = Currency.entries

    override val selectedCurrency = MutableStateFlow(currencies[0])

    override val coinsPagedState: StateFlow<PagedState<PagedCoins>> = MutableStateFlow(
        PagedState(
            data = PagedCoins(
                coins = Coin.MOCKS,
                hasNextPage = true
            )
        )
    )

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onCoinClick(coinId: CoinId) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit

    override fun onLoadNext() = Unit
}