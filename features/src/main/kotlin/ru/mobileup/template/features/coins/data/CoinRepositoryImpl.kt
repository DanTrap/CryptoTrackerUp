package ru.mobileup.template.features.coins.data

import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedPhysicalReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.keyed_paged.KeyedPagedFetcher
import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import me.aartikov.replica.paged.PagedData
import me.aartikov.replica.paged.PagedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.coins.data.dto.CoinDetailsResponse.Companion.toDomain
import ru.mobileup.template.features.coins.data.dto.CoinResponse.Companion.toDomain
import ru.mobileup.template.features.coins.data.dto.CoinSearchDto.Companion.toDomain
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.CoinDetails
import ru.mobileup.template.features.coins.domain.CoinSearch
import ru.mobileup.template.features.coins.domain.Currency
import ru.mobileup.template.features.coins.domain.PagedCoins
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class CoinRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CoinApi,
) : CoinRepository {

    companion object {
        private const val PAGE_SIZE = 30
    }

    override val coinsPagedReplica: KeyedPagedReplica<Currency, PagedCoins> =
        replicaClient.createKeyedPagedReplica(
            name = "coinsPaged",
            childName = { currency -> "currency = $currency" },
            childSettings = { PagedReplicaSettings(staleTime = 15.minutes, clearTime = 2.minutes) },
            idExtractor = { it.id },
            fetcher = object : KeyedPagedFetcher<Currency, Coin, CoinPage> {

                override suspend fun fetchFirstPage(key: Currency): CoinPage {
                    val coins = api.getCoins(
                        currency = key.name.lowercase(),
                        page = 1,
                        itemsPerPage = PAGE_SIZE
                    ).map { it.toDomain() }

                    return CoinPage(items = coins, hasNextPage = true)
                }

                override suspend fun fetchNextPage(
                    key: Currency,
                    currentData: PagedData<Coin, CoinPage>,
                ): CoinPage {
                    val nextPage = currentData.items.size / PAGE_SIZE + 1

                    val coins = api.getCoins(
                        currency = key.name.lowercase(),
                        page = nextPage,
                        itemsPerPage = PAGE_SIZE
                    ).map { it.toDomain() }

                    return CoinPage(
                        items = coins,
                        hasNextPage = coins.size == PAGE_SIZE || coins.isNotEmpty()
                    )
                }
            }
        ).map { _, pagedData ->
            PagedCoins(pagedData.items, hasNextPage = pagedData.hasNextPage)
        }

    override val coinDetailsReplica: KeyedPhysicalReplica<String, CoinDetails> =
        replicaClient.createKeyedReplica(
            name = "coinDetailsById",
            childName = { id -> "id = $id" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.minutes,
                    clearTime = 60.seconds
                )
            },
            settings = KeyedReplicaSettings(maxCount = 5)
        ) { id ->
            api.getCoinDetailsById(id).toDomain()
        }

    override val coinsSearchReplica: KeyedPhysicalReplica<String, List<CoinSearch>> =
        replicaClient.createKeyedReplica(
            name = "coinsSearch",
            childName = { query -> "query = $query" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.minutes,
                    clearTime = 60.seconds
                )
            },
        ) { query ->
            api.getCoinsByQuery(query).coins.map { it.toDomain() }
        }
}