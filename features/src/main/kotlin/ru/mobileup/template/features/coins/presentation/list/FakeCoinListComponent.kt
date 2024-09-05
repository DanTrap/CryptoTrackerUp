package ru.mobileup.template.features.coins.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.PagedState
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.Currency
import ru.mobileup.template.features.coins.domain.PagedCoins

class FakeCoinListComponent : CoinListComponent {

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

    override fun onCoinClick(coinId: String) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit

    override fun onLoadNext() = Unit
}