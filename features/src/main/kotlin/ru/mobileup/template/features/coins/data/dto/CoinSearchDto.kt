package ru.mobileup.template.features.coins.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.features.coins.domain.CoinId
import ru.mobileup.template.features.coins.domain.CoinSearch

@Serializable
data class CoinSearchDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("market_cap_rank") val marketCapRank: Int?,
    @SerialName("large") val image: String,
) {
    companion object {
        fun CoinSearchDto.toDomain(): CoinSearch = CoinSearch(
            id = CoinId(id),
            name = name,
            symbol = symbol,
            marketCapRank = marketCapRank ?: Int.MIN_VALUE,
            image = image,
        )
    }
}