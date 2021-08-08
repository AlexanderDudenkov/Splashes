package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.data.remote.UnsplashApiService
import com.dudencovgmail.splashes.data.remote.utils.Constants
import com.dudencovgmail.splashes.di.annotations.UnsplashRetrofit
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UnsplashApiServiceModule {

    @Provides
    @Singleton
    fun provideUnsplashApiService(@UnsplashRetrofit retrofit: Retrofit): UnsplashApiService =
        retrofit.create(UnsplashApiService::class.java)

    @UnsplashRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()

}