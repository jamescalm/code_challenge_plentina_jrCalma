package com.plentina.codingchallengeplentinajcalma.di.component

import android.app.Application
import com.plentina.codingchallengeplentinajcalma.DotaHeroesApp
import com.plentina.codingchallengeplentinajcalma.di.builder.ActivityBuilder
import com.plentina.codingchallengeplentinajcalma.di.builder.FragmentBuilder
import com.plentina.codingchallengeplentinajcalma.di.module.AppModule
import com.plentina.codingchallengeplentinajcalma.di.module.NetModule
import com.plentina.codingchallengeplentinajcalma.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetModule::class,
        ViewModelModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(app : DotaHeroesApp)
}