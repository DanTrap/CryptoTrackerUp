package ru.mobileup.template.features.coins.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.features.R
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.Currency

@Composable
internal fun CoinItem(
    coin: Coin,
    currency: Currency,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier.size(48.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(coin.image)
                .crossfade(true)
                .build(),
            contentDescription = coin.name,
            contentScale = ContentScale.Crop
        )
        CoinDetails(
            modifier = Modifier.weight(1f),
            name = coin.name,
            symbol = coin.symbol
        )
        CoinPriceDetails(
            currentPrice = coin.currentPrice,
            currency = currency,
            priceChangePercentage24h = coin.priceChangePercentage24h
        )
    }
}

@Composable
private fun CoinDetails(
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

@Composable
private fun CoinPriceDetails(
    currentPrice: Double,
    currency: Currency,
    priceChangePercentage24h: Double,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(
                R.string.price_format,
                currency.symbol,
                currentPrice.formatCurrentPrice()
            ),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.End
        )
        Text(
            text = priceChangePercentage24h.formatPricePercentage(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
            color = when {
                priceChangePercentage24h == 0.0 -> CustomTheme.colors.text.secondary
                priceChangePercentage24h > 0 -> CustomTheme.colors.text.positiveChangeColor
                else -> CustomTheme.colors.text.negativeChangeColor
            }
        )
    }
}

@Preview
@Composable
private fun CoinItemPreview() {
    AppTheme {
        CoinItem(
            coin = Coin.MOCK,
            currency = Currency.USD,
            onClick = {}
        )
    }
}