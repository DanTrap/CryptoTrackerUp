package ru.mobileup.template.features.coins.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import ru.mobileup.template.features.coins.data.dto.CoinResponse

interface CoinApi {

    @GET("api/v3/coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String,
        @Query("per_page") itemsPerPage: Int = 30,
    ): List<CoinResponse>
}
