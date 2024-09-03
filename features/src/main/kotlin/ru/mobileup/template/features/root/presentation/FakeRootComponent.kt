package ru.mobileup.template.features.root.presentation

import ru.mobileup.template.core.message.presentation.FakeMessageComponent
import ru.mobileup.template.core.utils.createFakeChildStackStateFlow
import ru.mobileup.template.features.coins.presentation.FakeCoinsComponent

class FakeRootComponent : RootComponent {

    override val childStack = createFakeChildStackStateFlow(
        RootComponent.Child.Coins(FakeCoinsComponent())
    )

    override val messageComponent = FakeMessageComponent()
}
