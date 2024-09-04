package ru.mobileup.template.features.coins.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.CoinDetails
import ru.mobileup.template.features.coins.domain.Currency

interface CoinRepository {

    val coins: KeyedReplica<Currency, List<Coin>>

    val coinDetailsById: KeyedReplica<String, CoinDetails>
}