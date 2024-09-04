package ru.mobileup.template.features.coins.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.coins.presentation.details.CoinDetailsUi
import ru.mobileup.template.features.coins.presentation.list.CoinListUi

@Composable
internal fun CoinsUi(
    component: CoinsComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.collectAsState()

    Children(stack = childStack, modifier = modifier) { child ->
        when (val instance = child.instance) {
            is CoinsComponent.Child.List -> CoinListUi(component = instance.component)
            is CoinsComponent.Child.Details -> CoinDetailsUi(component = instance.component)
        }
    }
}

@Preview
@Composable
private fun CoinsUiPreview() {
    AppTheme {
        CoinsUi(FakeCoinsComponent())
    }
}
