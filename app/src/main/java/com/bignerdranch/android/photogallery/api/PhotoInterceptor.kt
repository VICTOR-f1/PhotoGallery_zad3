package com.bignerdranch.android.photogallery.api

import android.app.DownloadManager
import okhttp3.HttpUrl
import okhttp3.Interceptor

private const val API_KEY = "вашApiКлюч"
class PhotoInterceptor : Interceptor {
    override fun intercept(chain:
                           Interceptor.Chain): Response {
        val originalRequest: Request =
            chain.request()
        val newUrl: HttpUrl =
            originalRequest.url().newBuilder()
                .addQueryParameter("api_key",
                    API_KEY)
                .addQueryParameter("format",
                    "json")
                .addQueryParameter("nojsoncallback"
                    ,
                    "1")
                .addQueryParameter("extras",
                    "url_s")
                .addQueryParameter("safesearch"
                    ,
                    "1")
                .build()
        val newRequest: DownloadManager.Request =
            originalRequest.newBuilder()
                .url(newUrl)
                .build()
        return chain.proceed(newRequest)
    }
}