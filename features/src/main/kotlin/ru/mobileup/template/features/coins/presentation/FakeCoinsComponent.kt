package ru.mobileup.template.features.coins.presentation

import ru.mobileup.template.core.utils.createFakeChildStackStateFlow
import ru.mobileup.template.features.coins.presentation.list.FakeCoinListComponent

class FakeCoinsComponent : CoinsComponent {

    override val childStack = createFakeChildStackStateFlow(
        CoinsComponent.Child.List(FakeCoinListComponent())
    )
}
