package ru.mobileup.template.features.coins.presentation.details

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coins.domain.CoinDetails

class FakeCoinDetailsComponent : CoinDetailsComponent {

    override val coinDetailsState: StateFlow<LoadableState<CoinDetails>> = MutableStateFlow(
        LoadableState(data = CoinDetails.MOCK)
    )

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}