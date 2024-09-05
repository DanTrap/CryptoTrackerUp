package ru.mobileup.template.features.coins.domain

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class CoinDetailsId(val value: String)

data class CoinDetails(
    val id: CoinDetailsId,
    val symbol: String,
    val name: String,
    val categories: List<String>,
    val description: String,
    val image: String,
) {
    companion object {
        val MOCK = CoinDetails(
            id = CoinDetailsId("bitcoin"),
            symbol = "BTC",
            name = "Bitcoin",
            image = "",
            categories = listOf("Cryptocurrency", "Layer 1 (L1)", "FTX Holdings"),
            description = "Bitcoin is the first successful internet money based on peer-to-peer technology; whereby no central bank or authority is involved in the transaction and production of the Bitcoin currency. It was created by an anonymous individual/group under the name"
        )
    }
}
