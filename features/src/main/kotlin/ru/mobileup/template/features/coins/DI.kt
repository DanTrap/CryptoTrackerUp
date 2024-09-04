package ru.mobileup.template.features.coins

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.coins.data.CoinApi
import ru.mobileup.template.features.coins.data.CoinRepository
import ru.mobileup.template.features.coins.data.CoinRepositoryImpl
import ru.mobileup.template.features.coins.presentation.CoinsComponent
import ru.mobileup.template.features.coins.presentation.RealCoinsComponent
import ru.mobileup.template.features.coins.presentation.details.CoinDetailsComponent
import ru.mobileup.template.features.coins.presentation.details.RealCoinDetailsComponent
import ru.mobileup.template.features.coins.presentation.list.CoinListComponent
import ru.mobileup.template.features.coins.presentation.list.RealCoinListComponent

val coinsModule = module {
    single<CoinApi> { get<NetworkApiFactory>().authorizedKtorfit.create() }
    single<CoinRepository> { CoinRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createCoinsComponent(
    componentContext: ComponentContext,
): CoinsComponent {
    return RealCoinsComponent(componentContext, get())
}

fun ComponentFactory.createCoinListComponent(
    componentContext: ComponentContext,
    onOutput: (CoinListComponent.Output) -> Unit,
): CoinListComponent {
    return RealCoinListComponent(componentContext, get(), get(), onOutput)
}

fun ComponentFactory.createCoinDetailsComponent(
    coinId: String,
    componentContext: ComponentContext,
): CoinDetailsComponent {
    return RealCoinDetailsComponent(coinId, componentContext, get(), get())
}