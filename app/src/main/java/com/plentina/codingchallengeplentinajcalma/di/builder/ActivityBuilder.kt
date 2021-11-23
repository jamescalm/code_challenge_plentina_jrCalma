package com.plentina.codingchallengeplentinajcalma.di.builder

import com.plentina.codingchallengeplentinajcalma.di.scope.ActivityScope
import com.plentina.codingchallengeplentinajcalma.ui.mainActivity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}