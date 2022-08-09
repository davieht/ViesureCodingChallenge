package com.daveloper.viesurecodingchallenge.utils

import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

private const val Delay = 2000L

suspend fun <T> fetch(
    retry: Int = 0,
    maxRetries: Int = 3,
    block: suspend () -> T,
    fetchFromDatabase: suspend () -> T,
    insertToDatabase: suspend (T) -> Unit
): Resource<T> {
    try {
        val result = block.invoke()
        insertToDatabase.invoke(result)
        return Resource.Success(fetchFromDatabase.invoke())
    } catch (e: Exception) {
        when (e) {
            is IOException,
            is HttpException -> {
                return if (retry < maxRetries) {
                    delay(Delay)
                    fetch(
                        retry = retry + 1,
                        block = block,
                        fetchFromDatabase = fetchFromDatabase,
                        insertToDatabase = insertToDatabase
                    )
                } else {
                    Resource.Error(
                        UiText.StringResource(R.string.network_error),
                        fetchFromDatabase.invoke()
                    )
                }
            }
            else -> return Resource.Error(
                if (e.localizedMessage != null)
                    UiText.DynamicString(e.localizedMessage as String)
                else
                    UiText.StringResource(R.string.unknown_error)
            )
        }
    }
}

