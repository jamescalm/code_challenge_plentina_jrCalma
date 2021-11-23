package com.plentina.codingchallengeplentinajcalma.api

import com.plentina.codingchallengeplentinajcalma.model.DotaHero
import io.reactivex.Observable
import retrofit2.http.GET

interface HeroesHttp {
    @GET("/api/heroStats")
    fun getHeroes() : Observable<List<DotaHero>>
}