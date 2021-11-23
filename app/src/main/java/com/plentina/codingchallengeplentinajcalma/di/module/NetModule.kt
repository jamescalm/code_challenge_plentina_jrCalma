package com.plentina.codingchallengeplentinajcalma.di.module

import com.plentina.codingchallengeplentinajcalma.DotaHeroesApp
import com.plentina.codingchallengeplentinajcalma.api.HeroesHttp
import com.plentina.codingchallengeplentinajcalma.api.interceptor.InternetConnectionInterceptor
import com.plentina.codingchallengeplentinajcalma.model.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {
    companion object {
        const val API_NEW : String = "API_NEW"
    }

    @Provides
    @Singleton
    fun provideInternetInterceptor(app:DotaHeroesApp) = InternetConnectionInterceptor(app)

    @Provides
    @Singleton
    fun providesOkHttpClient(internetConnectionInterceptor: InternetConnectionInterceptor) : OkHttpClient {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(internetConnectionInterceptor)
            .hostnameVerifier { _, _ -> true }
            .build()
    }

    @Provides
    @Singleton
    @Named(API_NEW)
    fun providesRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesHeroesHttp(@Named(API_NEW) retrofit: Retrofit) : HeroesHttp = retrofit.create(HeroesHttp::class.java)
}