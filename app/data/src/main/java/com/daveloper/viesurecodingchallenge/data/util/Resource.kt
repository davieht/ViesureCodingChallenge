package com.daveloper.viesurecodingchallenge.data.util

sealed class Resource<T>(
    val data: T? = null
) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(val errorMsg: UiText, data: T? = null): Resource<T>(data)
}