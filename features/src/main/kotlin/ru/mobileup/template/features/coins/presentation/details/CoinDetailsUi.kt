package ru.mobileup.template.features.coins.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.utils.dispatchOnBackPressed
import ru.mobileup.template.core.widget.PullRefreshLceWidget
import ru.mobileup.template.core.widget.RefreshingProgress
import ru.mobileup.template.features.R
import ru.mobileup.template.features.coins.domain.CoinDetails

@Composable
fun CoinDetailsUi(
    component: CoinDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val pokemonState by component.coinDetailsState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        CoinDetailsTopBar(title = pokemonState.data?.name ?: "")
        PullRefreshLceWidget(
            state = pokemonState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick
        ) { coinDetails, refreshing ->
            CoinDetailsContent(coinDetails = coinDetails)
            RefreshingProgress(refreshing, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
private fun CoinDetailsTopBar(title: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = CustomTheme.colors.background.screen,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { dispatchOnBackPressed(context) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_description_arrow_back)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.87f)
                )
            )
        }
    }
}

@Composable
private fun CoinDetailsContent(
    coinDetails: CoinDetails,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(96.dp)
                .align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(LocalContext.current)
                .data(coinDetails.image)
                .crossfade(true)
                .build(),
            contentDescription = coinDetails.name,
            contentScale = ContentScale.Crop
        )
        TitleBodyItem(
            title = stringResource(R.string.description),
            body = coinDetails.description
        )
        TitleBodyItem(
            modifier = Modifier.padding(bottom = 16.dp),
            title = stringResource(R.string.categories),
            body = coinDetails.categories.joinToString()
        )
    }
}

@Composable
private fun TitleBodyItem(
    title: String,
    body: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun CoinDetailsPreview() {
    AppTheme {
        CoinDetailsUi(component = FakeCoinDetailsComponent())
    }
}