package ru.mobileup.template.core.utils

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.triggerLoadNext(
    hasNextPage: Boolean,
    threshold: Int = 2,
    onLoadNext: () -> Unit,
) {
    if (hasNextPage) {
        val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        val totalItems = layoutInfo.totalItemsCount

        if (lastVisibleIndex >= (totalItems - threshold)) onLoadNext()
    }
}