package com.hegunhee.todolistwithflow.di

import com.hegunhee.todolistwithflow.BuildConfig
import com.hegunhee.todolistwithflow.api.TodoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideConverterFactory(
        json : Json
    ) : Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideTodoApi(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ) : TodoApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.7:8080/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
            .create(TodoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
    }
    @Provides
    @Singleton
    fun provideJson() : Json = Json {
        ignoreUnknownKeys = true
    }
}