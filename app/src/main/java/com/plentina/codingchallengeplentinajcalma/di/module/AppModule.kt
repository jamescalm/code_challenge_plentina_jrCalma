package com.plentina.codingchallengeplentinajcalma.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.plentina.codingchallengeplentinajcalma.DotaHeroesApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providesHeroListApplication(app: Application) :DotaHeroesApp = app as DotaHeroesApp

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesPreferences(context: Context) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}