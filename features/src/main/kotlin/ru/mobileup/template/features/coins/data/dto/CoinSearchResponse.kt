package ru.mobileup.template.features.coins.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinSearchResponse(
    @SerialName("coins") val coins: List<CoinSearchDto>,
)