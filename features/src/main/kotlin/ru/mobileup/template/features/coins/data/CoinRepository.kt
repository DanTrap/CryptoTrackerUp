package ru.mobileup.template.features.coins.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.CoinDetails
import ru.mobileup.template.features.coins.domain.Currency

interface CoinRepository {

    val coinsReplica: KeyedReplica<Currency, List<Coin>>

    val coinDetailsReplica: KeyedReplica<String, CoinDetails>
}