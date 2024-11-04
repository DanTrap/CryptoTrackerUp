package ru.mobileup.template.features.coins.presentation.search

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeCoinsSearchComponent : CoinsSearchComponent {

    override val searchState: StateFlow<String> = MutableStateFlow("aboba")

    override val isValidQueryState: StateFlow<Boolean> = MutableStateFlow(true)

    override fun onValueChange(value: String) = Unit
}