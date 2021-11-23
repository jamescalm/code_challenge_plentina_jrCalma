package com.plentina.codingchallengeplentinajcalma.api.interceptor

import com.plentina.codingchallengeplentinajcalma.DotaHeroesApp
import com.plentina.codingchallengeplentinajcalma.R
import okhttp3.Interceptor
import okhttp3.Response

class InternetConnectionInterceptor(var app: DotaHeroesApp) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!app.isAppOnline()){
            app.showMessage(R.string.internet_unavailable)
        }
        return chain.proceed(chain.request())
    }
}