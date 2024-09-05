package ru.mobileup.template.features.coins.presentation.details

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.coins.data.CoinRepository

class RealCoinDetailsComponent(
    componentContext: ComponentContext,
    coinId: String,
    coinRepository: CoinRepository,
    errorHandler: ErrorHandler,
) : ComponentContext by componentContext, CoinDetailsComponent {

    private val coinDetailsReplica = coinRepository.coinDetailsReplica.withKey(coinId)

    override val coinDetailsState = coinDetailsReplica.observe(this, errorHandler)

    override fun onRetryClick() = coinDetailsReplica.refresh()

    override fun onRefresh() = coinDetailsReplica.refresh()
}