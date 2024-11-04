package ru.mobileup.template.features.coins.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import ru.mobileup.template.features.coins.data.dto.CoinDetailsResponse
import ru.mobileup.template.features.coins.data.dto.CoinResponse
import ru.mobileup.template.features.coins.data.dto.CoinSearchResponse

interface CoinApi {

    @GET("api/v3/coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int,
    ): List<CoinResponse>

    @GET("api/v3/coins/{id}")
    suspend fun getCoinDetailsById(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = false,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
    ): CoinDetailsResponse

    @GET("api/v3/search")
    suspend fun getCoinsByQuery(
        @Query("query") query: String,
    ): CoinSearchResponse
}