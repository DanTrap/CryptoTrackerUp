package ru.mobileup.template.features.coins.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.custom.CustomTheme

@Composable
fun CoinDetails(
    name: String,
    symbol: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.text.primary
            )
        )
        Text(
            text = symbol,
            style = MaterialTheme.typography.bodyLarge.copy(color = CustomTheme.colors.text.secondary)
        )
    }
}