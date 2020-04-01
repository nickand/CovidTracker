package com.example.covidtracker.util

import com.example.covidtracker.data.model.ErrorResponse

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null): ResultWrapper<Nothing>()
    data class Error(val exception: String?) : ResultWrapper<Nothing>()
}