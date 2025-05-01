package ru.itis.android2sem.data.remote.api

import retrofit2.Response
import java.lang.Exception

suspend fun <T> handleApiResponse(
    execute: suspend () -> Response<T>
): T {
    val response = execute()
    if (response.isSuccessful) {
        return response.body() ?: throw Exception("Empty response body")
    } else {
        val error = when (response.code()) {
            401 -> "Unauthorized - check your credentials"
            403 -> "Forbidden - access denied"
            404 -> "Not found - invalid endpoint"
            in 500..599 -> "Server error - try again later"
            else -> "Unknown error occurred"
        }
        throw Exception(error)
    }
}