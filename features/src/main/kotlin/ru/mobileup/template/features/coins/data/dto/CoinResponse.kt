package ru.mobileup.template.features.coins.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.features.coins.domain.Coin

@Serializable
data class CoinResponse(
    @SerialName("current_price") val currentPrice: Double,
    @SerialName("id") val id: String,
    @SerialName("image") val image: String,
    @SerialName("name") val name: String,
    @SerialName("price_change_percentage_24h") val priceChangePercentage24h: Double,
    @SerialName("symbol") val symbol: String,
) {
    companion object {
        fun CoinResponse.toDomain(): Coin = Coin(
            id = id,
            name = name,
            symbol = symbol.uppercase(),
            image = image,
            currentPrice = currentPrice,
            priceChangePercentage24h = priceChangePercentage24h
        )
    }
}
