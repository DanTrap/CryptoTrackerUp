package ru.mobileup.template.features.coins.data

import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import ru.mobileup.template.features.coins.domain.CoinDetails
import ru.mobileup.template.features.coins.domain.CoinSearch
import ru.mobileup.template.features.coins.domain.Currency
import ru.mobileup.template.features.coins.domain.PagedCoins

interface CoinRepository {

    val coinsPagedReplica: KeyedPagedReplica<Currency, PagedCoins>

    val coinDetailsReplica: KeyedReplica<String, CoinDetails>

    val coinsSearchReplica: KeyedReplica<String, List<CoinSearch>>
}