package com.plentina.codingchallengeplentinajcalma

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.mcxiaoke.koi.ext.toast
import com.plentina.codingchallengeplentinajcalma.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.lang.Exception
import javax.inject.Inject

class DotaHeroesApp: Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private var connectivityManager : ConnectivityManager? = null

    override fun onCreate() {
        super.onCreate()
        setupComponents()
        sharedPreferences = getSharedPreferences("dotaheroesapp.pref", Context.MODE_PRIVATE)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setupComponents(){
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        try {
            connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    fun isAppOnline(): Boolean {
        val netInfo = connectivityManager?.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun showMessage(stringId: Int) {
        var subs = Single.just(stringId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                toast(it)
            } ,{})
    }

    companion object{
        var sharedPreferences: SharedPreferences? = null
    }
}