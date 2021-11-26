package com.plentina.codingchallengeplentinajcalma.di.module

import com.plentina.codingchallengeplentinajcalma.ViewModelFactory
import com.plentina.codingchallengeplentinajcalma.api.HeroesHttp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun providesHeroListViewModelFactory(heroes: HeroesHttp): ViewModelFactory {
        return ViewModelFactory(heroes)
    }

    @Provides
    @Singleton
    fun providesHeroDetailViewModelFactory(heroes: HeroesHttp): ViewModelFactory {
        return ViewModelFactory(heroes)
    }
}