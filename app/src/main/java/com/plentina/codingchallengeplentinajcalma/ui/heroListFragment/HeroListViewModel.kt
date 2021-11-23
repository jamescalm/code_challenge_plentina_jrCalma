package com.plentina.codingchallengeplentinajcalma.ui.heroListFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plentina.codingchallengeplentinajcalma.api.HeroesHttp
import com.plentina.codingchallengeplentinajcalma.model.DotaHero
import com.plentina.codingchallengeplentinajcalma.util.LoadingState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeroListViewModel @Inject constructor(private val heroesHttp: HeroesHttp) : ViewModel() {
    private val disposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private var _heroList = MutableLiveData<List<DotaHero>>()
    var heroList : LiveData<List<DotaHero>> = _heroList


    private var _heroListLoader = MutableLiveData<LoadingState>()
    var heroListLoader : LiveData<LoadingState> = _heroListLoader

    val TAG = "HeroesListVM"

    /** This function gets the value list of tracks from the API endpoint*/
    fun getHeroList(){
        heroesHttp.getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                Log.d(TAG, "Subscribing...")
                _heroListLoader.value = LoadingState.loading()
            }
            .subscribe ({
                Log.d(TAG, "${it.size}")
                _heroList.value = it
                _heroListLoader.value = LoadingState.success("Successfully loaded Track List")
            }, {
                Log.d(TAG, "Error: ${it.localizedMessage}")
                it.printStackTrace()
                _heroListLoader.value = LoadingState.error("Failed to load Track List")
            })
            .addTo(disposables)

    }
}