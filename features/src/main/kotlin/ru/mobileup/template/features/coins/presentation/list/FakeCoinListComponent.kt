package ru.mobileup.template.features.coins.presentation.list

import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coins.domain.Coin
import ru.mobileup.template.features.coins.domain.Currency

class FakeCoinListComponent : CoinListComponent {

    override val currencies: List<Currency> = Currency.entries

    override val selectedCurrency = MutableStateFlow(currencies[0])

    override val coinsState = MutableStateFlow(
        LoadableState(
            loading = true,
            data = Coin.MOCKS
        )
    )

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onCoinClick(coinId: String) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}