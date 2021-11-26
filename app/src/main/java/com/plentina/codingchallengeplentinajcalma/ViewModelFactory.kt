package com.plentina.codingchallengeplentinajcalma

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plentina.codingchallengeplentinajcalma.api.HeroesHttp
import com.plentina.codingchallengeplentinajcalma.ui.heroDetail.HeroDetailViewModel
import com.plentina.codingchallengeplentinajcalma.ui.heroListFragment.HeroListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val api: HeroesHttp) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroListViewModel::class.java)) {
            return HeroListViewModel(api) as T
        }
        if (modelClass.isAssignableFrom(HeroDetailViewModel::class.java)) {
            return HeroDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}