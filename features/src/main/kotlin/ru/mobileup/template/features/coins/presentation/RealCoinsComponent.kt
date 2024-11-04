package ru.mobileup.template.features.coins.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.utils.toStateFlow
import ru.mobileup.template.features.coins.createCoinDetailsComponent
import ru.mobileup.template.features.coins.createCoinListComponent
import ru.mobileup.template.features.coins.domain.CoinId
import ru.mobileup.template.features.coins.presentation.list.CoinListComponent

internal class RealCoinsComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
) : ComponentContext by componentContext, CoinsComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        serializer = ChildConfig.serializer(),
        initialConfiguration = ChildConfig.List,
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext,
    ): CoinsComponent.Child = when (config) {
        ChildConfig.List -> {
            CoinsComponent.Child.List(
                componentFactory.createCoinListComponent(componentContext, ::onCoinListOutput)
            )
        }

        is ChildConfig.Details -> {
            CoinsComponent.Child.Details(
                componentFactory.createCoinDetailsComponent(componentContext, config.coinId)
            )
        }
    }

    private fun onCoinListOutput(output: CoinListComponent.Output) {
        when (output) {
            is CoinListComponent.Output.CoinDetailsRequested -> {
                navigation.push(ChildConfig.Details(output.coinId))
            }
        }
    }

    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object List : ChildConfig

        @Serializable
        data class Details(val coinId: CoinId) : ChildConfig
    }
}
