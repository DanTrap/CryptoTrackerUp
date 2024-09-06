package ru.mobileup.template.features.coins.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.utils.fromHtml
import ru.mobileup.template.features.coins.domain.CoinDetails
import ru.mobileup.template.features.coins.domain.CoinId

@Serializable
data class CoinDetailsResponse(
    @SerialName("id") val id: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("name") val name: String,
    @SerialName("categories") val categories: List<String>,
    @SerialName("description") val description: Description,
    @SerialName("image") val image: Image,
) {
    companion object {
        fun CoinDetailsResponse.toDomain(): CoinDetails = CoinDetails(
            id = CoinId(id),
            symbol = symbol,
            name = name,
            categories = categories,
            description = description.en.fromHtml(),
            image = image.large
        )
    }
}