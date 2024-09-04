package ru.mobileup.template.features.coins.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.features.coins.presentation.details.CoinDetailsComponent
import ru.mobileup.template.features.coins.presentation.list.CoinListComponent

interface CoinsComponent {

    val childStack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {
        class List(val component: CoinListComponent) : Child
        class Details(val component: CoinDetailsComponent) : Child
    }
}