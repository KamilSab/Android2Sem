package ru.itis.android2sem.data.remote.interceptors

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.android2sem.BuildConfig

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", getAuthToken())
            .build()
        return chain.proceed(request)
    }

    private fun getAuthToken(): String {
        val credentials = "${BuildConfig.CATAAS_USER}:${BuildConfig.CATAAS_PASSWORD}"
        return "Basic " + Base64.encodeToString(
            credentials.toByteArray(),
            Base64.NO_WRAP
        )
    }
}