package ru.mobileup.template.features.coins.domain

data class CoinSearch(
    val id: CoinId,
    val name: String,
    val symbol: String,
    val marketCapRank: Int,
    val image: String,
)