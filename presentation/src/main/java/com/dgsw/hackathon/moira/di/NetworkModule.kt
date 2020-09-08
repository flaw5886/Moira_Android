package com.dgsw.hackathon.moira.di

import com.dgsw.hackathon.moira.network.interceptor.TokenInterceptor
import com.dgsw.hackathon.moira.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

val netWorkModule = module {
    single { TokenInterceptor(androidApplication()) }
    single { provideOkhttpClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideOkhttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient().newBuilder().apply {
        addInterceptor(interceptor)
        addInterceptor(tokenInterceptor)
    }.build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.TEST_HOST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()
}