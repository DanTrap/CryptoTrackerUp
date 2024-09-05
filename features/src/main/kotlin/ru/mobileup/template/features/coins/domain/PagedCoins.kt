package ru.mobileup.template.features.coins.domain

data class PagedCoins(
    val coins: List<Coin>,
    val hasNextPage: Boolean
)