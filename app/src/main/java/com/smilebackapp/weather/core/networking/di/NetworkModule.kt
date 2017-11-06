package com.smilebackapp.weather.core.networking.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
open class NetworkModule {

    companion object {
        private val API_URL: String = ""
    }

    @Provides
    @Named("baseUrl")
    open fun providesBaseUrl(): String = API_URL



    @Provides
    fun providesRxJava2CallAdapter(): RxJava2CallAdapterFactory
            = RxJava2CallAdapterFactory.create()

    @Provides
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory
            = GsonConverterFactory.create(gson)

    @Provides
    fun providesGson(): Gson = GsonBuilder()
            .setDateFormat("dd-mm-yyyy hh:mm:ss")
            .create()

    @Provides
    fun providesOkHttpClient(logger: Interceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

    @Provides
    fun providesInterceptorLogger(): Interceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @Named("retrofitDefault")
    fun providesRetrofit(rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                         @Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient,
                         gsonConverterFactory: GsonConverterFactory): Retrofit
            = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(baseUrl)
            .build()
}