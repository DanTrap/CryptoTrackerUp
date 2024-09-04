package ru.mobileup.template.features.coins.presentation.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.features.coins.domain.Currency

@Composable
fun CoinCurrencyItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
        },
        shape = MaterialTheme.shapes.extraLarge,
        border = null,
        colors = FilterChipDefaults.filterChipColors(
            labelColor = CustomTheme.colors.text.primary,
            selectedLabelColor = CustomTheme.colors.text.invert,
            containerColor = CustomTheme.colors.button.secondary,
            selectedContainerColor = CustomTheme.colors.button.primary
        )
    )
}

@Preview
@Composable
private fun CoinCurrencyItemPreview() {
    var isSelected by remember { mutableStateOf(false) }
    AppTheme {
        CoinCurrencyItem(
            text = Currency.USD.name,
            isSelected = isSelected,
            onClick = {
                isSelected = !isSelected
            }
        )
    }
}