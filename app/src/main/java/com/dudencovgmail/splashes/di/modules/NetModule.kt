package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.BuildConfig
import com.dudencovgmail.splashes.di.scopes.ApplicationScope
import com.dudencovgmail.splashes.repository.remote.IApiService
import com.dudencovgmail.splashes.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {

    @Provides
    @ApplicationScope
    fun provideApiService(retrofit: Retrofit): IApiService = retrofit.create(IApiService::class.java)

    @Provides
    @ApplicationScope
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    @ApplicationScope
    fun provideGson(builder: GsonBuilder): Gson = builder.setLenient().create()

    @Provides
    @ApplicationScope
    fun provideGsonBuilder(): GsonBuilder = GsonBuilder()

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().apply {
                retryOnConnectionFailure(true)
                networkInterceptors().add(interceptor)
            }.build()

    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
}