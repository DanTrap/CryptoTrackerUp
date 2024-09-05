package ru.mobileup.template.features.coins.presentation.details

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.coins.domain.CoinDetails

interface CoinDetailsComponent {

    val coinDetailsState: StateFlow<LoadableState<CoinDetails>>

    fun onRetryClick()

    fun onRefresh()
}