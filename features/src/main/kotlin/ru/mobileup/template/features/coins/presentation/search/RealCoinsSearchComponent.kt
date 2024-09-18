package ru.mobileup.template.features.coins.presentation.search

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealCoinsSearchComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, CoinsSearchComponent {

    companion object {
        // Строка может содержать только латинские буквы и пробелы
        private const val QUERY_REGEX = "^[A-Za-z ]+$"
        private const val MAX_QUERY_LENGTH = 20
    }

    private val _searchState: MutableStateFlow<String> = MutableStateFlow("")
    override val searchState: StateFlow<String> = _searchState

    private val _isValidQueryState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    override val isValidQueryState: StateFlow<Boolean> = _isValidQueryState

    override fun onValueChange(value: String) {
        _isValidQueryState.value = isValidQuery(value)
        _searchState.value = value
    }

    private fun isValidQuery(value: String): Boolean =
        Regex(QUERY_REGEX).matches(value) && value.length <= MAX_QUERY_LENGTH || value.isEmpty()
}