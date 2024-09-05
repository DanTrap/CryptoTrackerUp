package ru.mobileup.template.features.coins.presentation.list

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.utils.triggerLoadNext
import ru.mobileup.template.core.widget.EmptyPlaceholder
import ru.mobileup.template.core.widget.PullRefreshLceWidget
import ru.mobileup.template.core.widget.RefreshingProgress
import ru.mobileup.template.features.R
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.Currency

@Composable
internal fun CoinListUi(
    component: CoinListComponent,
    modifier: Modifier = Modifier,
) {
    val selectedCurrency by component.selectedCurrency.collectAsState()
    val coinsPagedState by component.coinsPagedState.collectAsState()
    val lazyListState = rememberLazyListState().apply {
        triggerLoadNext(
            hasNextPage = coinsPagedState.data?.hasNextPage ?: false,
            onLoadNext = component::onLoadNext
        )
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        color = CustomTheme.colors.background.screen
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CoinCurrencyTopBar(
                selectedCurrency = selectedCurrency,
                currencies = component.currencies,
                onChipClick = component::onCurrencyClick
            )
            PullRefreshLceWidget(
                state = coinsPagedState,
                onRefresh = component::onRefresh,
                onRetryClick = component::onRetryClick
            ) { pagedCoins, refreshing ->
                if (pagedCoins.coins.isNotEmpty()) {
                    CoinsListContent(
                        coins = pagedCoins.coins,
                        lazyListState = lazyListState,
                        selectedCurrency = selectedCurrency,
                        onCoinClick = component::onCoinClick,
                        showBottomProgressIndicator = coinsPagedState.loadingStatus == PagedLoadingStatus.LoadingNextPage
                    )
                } else {
                    EmptyPlaceholder(description = stringResource(R.string.coins_empty_description))
                }
                RefreshingProgress(refreshing)
            }
        }
    }
}

@Composable
private fun CoinCurrencyTopBar(
    selectedCurrency: Currency,
    currencies: List<Currency>,
    onChipClick: (Currency) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = CustomTheme.colors.background.screen,
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.statusBarsPadding()) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                text = stringResource(R.string.list_of_cryptocurrencies),
                style = CustomTheme.typography.title.regular
            )
            CurrencyChipsRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                selectedCurrency = selectedCurrency,
                currencies = currencies,
                onChipClick = onChipClick
            )
        }
    }
}

@Composable
private fun CurrencyChipsRow(
    selectedCurrency: Currency,
    currencies: List<Currency>,
    onChipClick: (Currency) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        currencies.forEach { currency ->
            CoinCurrencyItem(
                text = currency.name,
                isSelected = selectedCurrency == currency,
                onClick = { onChipClick(currency) },
            )
        }
    }
}

@Composable
private fun CoinsListContent(
    lazyListState: LazyListState,
    coins: List<Coin>,
    selectedCurrency: Currency,
    onCoinClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    showBottomProgressIndicator: Boolean = false,
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(coins, key = { it.id }) { coin ->
            CoinItem(
                coin = coin,
                currency = selectedCurrency,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onCoinClick(coin.id) }
            )
        }
        if (showBottomProgressIndicator) {
            item {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun CoinListUiPreview() {
    AppTheme {
        CoinListUi(component = FakeCoinListComponent())
    }
}