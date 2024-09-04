package ru.mobileup.template.features.coins.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("thumb") val thumb: String,
    @SerialName("small") val small: String,
    @SerialName("large") val large: String,
)