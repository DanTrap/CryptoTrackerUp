package ru.mobileup.template.features.coins.domain

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class CoinId(val value: String)

data class Coin(
    val id: CoinId,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
) {
    companion object {
        val MOCK = Coin(
            id = CoinId("tether"),
            name = "Tether",
            symbol = "USDT",
            image = "some_url",
            currentPrice = 435.01,
            priceChangePercentage24h = -1.345
        )
        val MOCKS = listOf(
            Coin(
                id = CoinId("bitcoin"),
                name = "Bitcoin",
                symbol = "BTC",
                image = "",
                currentPrice = 70187.0,
                priceChangePercentage24h = 3.12502
            ),
            Coin(
                id = CoinId("ethereum"),
                name = "Ethereum",
                symbol = "ETH",
                image = "",
                currentPrice = 2504.59,
                priceChangePercentage24h = -0.66152
            ),
            Coin(
                id = CoinId("tether"),
                name = "Tether",
                symbol = "USDT",
                image = "",
                currentPrice = 1.0,
                priceChangePercentage24h = -0.00213665816804
            ),
        )
    }
}
