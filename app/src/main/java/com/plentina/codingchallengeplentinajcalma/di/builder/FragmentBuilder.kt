package com.plentina.codingchallengeplentinajcalma.di.builder

import com.plentina.codingchallengeplentinajcalma.di.scope.FragmentScope
import com.plentina.codingchallengeplentinajcalma.ui.heroListFragment.HeroListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun provideHeroListFragment(): HeroListFragment
}