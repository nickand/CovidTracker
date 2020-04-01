package com.example.covidtracker.extensions

import com.example.covidtracker.util.ResultWrapper

suspend fun <T> safeApiCall(
    call: suspend () -> T,
    onSuccess: (ResultWrapper.Success<T>) -> Unit,
    onFailure: (ResultWrapper.Error) -> Unit
) {
    runCatching {
        val response = call()
        onSuccess.invoke(ResultWrapper.Success(response))
    }.onFailure {
        it.printStackTrace()
        onFailure.invoke(ResultWrapper.Error(it.message))
    }
}