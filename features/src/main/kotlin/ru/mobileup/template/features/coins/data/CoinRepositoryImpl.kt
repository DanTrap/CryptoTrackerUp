package ru.mobileup.template.features.coins.data

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedPhysicalReplica
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.coins.data.dto.CoinResponse.Companion.toDomain
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.Currency
import kotlin.time.Duration.Companion.seconds

class CoinRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CoinApi,
) : CoinRepository {

    override val coins: KeyedPhysicalReplica<Currency, List<Coin>> =
        replicaClient.createKeyedReplica(
            name = "coins",
            childName = { currency -> "currency = $currency" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 10.seconds,
                    clearTime = 60.seconds
                )
            }
        ) { currency ->
            api.getCoins(currency.name.lowercase()).map { it.toDomain() }
        }
}