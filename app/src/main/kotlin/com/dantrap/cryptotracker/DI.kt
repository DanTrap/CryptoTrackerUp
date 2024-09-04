package com.dantrap.cryptotracker

import ru.mobileup.template.core.coreModule
import ru.mobileup.template.features.coins.coinsModule
import ru.mobileup.template.features.pokemons.pokemonsModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL, BuildConfig.API_KEY),
    pokemonsModule,
    coinsModule
)