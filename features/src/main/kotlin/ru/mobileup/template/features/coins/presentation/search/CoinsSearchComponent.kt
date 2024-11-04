package ru.mobileup.template.features.coins.presentation.search

import kotlinx.coroutines.flow.StateFlow

interface CoinsSearchComponent {

    val searchState: StateFlow<String>

    val isValidQueryState: StateFlow<Boolean>

    fun onValueChange(value: String)
}