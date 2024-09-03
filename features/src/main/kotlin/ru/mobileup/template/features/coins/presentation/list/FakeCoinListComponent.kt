package ru.mobileup.template.features.coins.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.Currency

class FakeCoinListComponent : CoinListComponent {

    override val currencies: List<Currency> = Currency.entries

    override val selectedCurrency = MutableStateFlow(currencies[0])

    override val coinsState = MutableStateFlow(
        LoadableState(
            loading = true,
            data = listOf(
                Coin(
                    id = "bitcoin",
                    name = "Bitcoin",
                    symbol = "BTC",
                    image = "",
                    currentPrice = 70187.0,
                    priceChangePercentage24h = 3.12502
                ),
                Coin(
                    id = "ethereum",
                    name = "Ethereum",
                    symbol = "ETH",
                    image = "",
                    currentPrice = 2504.59,
                    priceChangePercentage24h = -0.66152
                ),
                Coin(
                    id = "tether",
                    name = "Tether",
                    symbol = "USDT",
                    image = "",
                    currentPrice = 1.0,
                    priceChangePercentage24h = -0.000213665816804376
                ),
            )
        )
    )

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onCoinClick(coinId: String) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}