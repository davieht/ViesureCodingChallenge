package com.daveloper.viesurecodingchallenge.data.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    class DynamicString(
        val s: String
    ) : UiText()

    class StringResource(
        @StringRes val resId: Int
    ) : UiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> s
            is StringResource -> stringResource(id = resId)
        }
    }
}