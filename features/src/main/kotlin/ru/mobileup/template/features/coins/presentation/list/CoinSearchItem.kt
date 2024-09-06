package ru.mobileup.template.features.coins.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.features.coins.domain.CoinSearch
import ru.mobileup.template.features.coins.presentation.widget.CoinDetails

@Composable
fun CoinSearchItem(
    coinSearch: CoinSearch,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier.size(48.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(coinSearch.image)
                .crossfade(true)
                .build(),
            contentDescription = coinSearch.name,
            contentScale = ContentScale.Crop
        )
        CoinDetails(
            modifier = Modifier.weight(1f),
            name = coinSearch.name,
            symbol = coinSearch.symbol
        )
        Text(text = coinSearch.marketCapRank.toString())
    }
}