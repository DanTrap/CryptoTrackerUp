package ru.mobileup.template.features.coins.domain

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
) {
    companion object {
        val MOCK = Coin(
            id = "tether",
            name = "Tether",
            symbol = "USDT",
            image = "some_url",
            currentPrice = 435.01,
            priceChangePercentage24h = -1.345
        )
    }
}
