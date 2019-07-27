/*
package com.dudencovgmail.splashes.repository.remote

import com.dudencovgmail.splashes.BuildConfig
import com.dudencovgmail.splashes.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object OkhttpTool {

    fun createOkHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        val okHttpClient = OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            networkInterceptors().add(logInterceptor)
        }
        return okHttpClient.build()
    }

    fun createHttpConnection(): apiService {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build()

        return retrofit.create(apiService::class.java)
    }
}*/
