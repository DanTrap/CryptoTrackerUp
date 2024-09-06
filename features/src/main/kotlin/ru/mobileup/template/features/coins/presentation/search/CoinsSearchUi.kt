package ru.mobileup.template.features.coins.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun CoinsSearchUi(
    component: CoinsSearchComponent,
    modifier: Modifier = Modifier,
) {
    val searchState by component.searchState.collectAsState()
    val isValidQueryState by component.isValidQueryState.collectAsState()

    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchState,
            onValueChange = component::onValueChange,
            singleLine = true,
            isError = !isValidQueryState,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )
    }
}