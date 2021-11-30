package com.plentina.codingchallengeplentinajcalma.ui.heroDetail

import android.util.Rational
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plentina.codingchallengeplentinajcalma.DotaHeroesApp
import com.plentina.codingchallengeplentinajcalma.R
import com.plentina.codingchallengeplentinajcalma.model.Constants
import com.plentina.codingchallengeplentinajcalma.model.DotaHero
import java.lang.Exception
import javax.inject.Inject

class HeroDetailViewModel @Inject constructor() : ViewModel() {

    private var _heroLevel = MutableLiveData<Int>()
    var heroLevel : LiveData<Int> = _heroLevel
    /** This function will be called when users enters a level on detail fragment
     *  This will change the hero level live data thus triggering the observer on the detail fragment*/
    fun setLevel(lvl: Int){
        _heroLevel.value = lvl
    }

    /** This function calculates Hero Minimum Attack according to Hero Level*/
    fun calculateMinAttack(heroLvl: Int, heroDetail: DotaHero): Int{
        val mainPrimaryAttr = heroDetail.getBasePrimAttr() + heroDetail.getPrimAttrGain()*(heroLvl -1)
        return try{
            heroDetail.base_attack_min!! + mainPrimaryAttr.toInt()
        }catch (e: Exception){
            0
        }

    }

    /** This function calculates Hero Maximum Attack according to Hero Level*/
    fun calculateMaxAttack(heroLvl: Int, heroDetail: DotaHero): Int{
        val mainPrimaryAttr = heroDetail.getBasePrimAttr() + heroDetail.getPrimAttrGain()*(heroLvl -1)
        return try{
            heroDetail.base_attack_max!! + mainPrimaryAttr.toInt()
        }catch (e: Exception){
            0
        }
    }

    /** This function calculates Hero Full Health Pool according to Hero Level*/
    fun calculateHP(heroLvl: Int, heroDetail: DotaHero): Int{
        val mainStr = heroDetail.base_str!! + heroDetail.str_gain!! *(heroLvl-1)
        return try{
            heroDetail.base_health!! + mainStr.toInt() * 20
        }catch (e: Exception){
            200
        }
    }

    /** This function calculates Hero Base Health Regen according to Hero Level*/
    fun calculateHPRegen(heroLvl: Int, heroDetail: DotaHero): Double{
        return try{
            heroDetail.base_health_regen!! + (heroDetail.base_str!! + heroDetail.str_gain!! *(heroLvl-1))*0.1
        }catch (e: Exception){
            0.0
        }
    }

    /** This function calculates Hero Full Mana according to Hero Level*/
    fun calculateMP(heroLvl: Int, heroDetail: DotaHero): Int{
        val mainInt = heroDetail.base_int!! + heroDetail.int_gain!! *(heroLvl-1)
        return try{
            heroDetail.base_mana!! + mainInt.toInt() * 12
        }catch (e: Exception){
            75
        }
    }

    /** This function calculates Hero Base Mana Regen according to Hero Level*/
    fun calculateMPRegen(heroLvl: Int, heroDetail: DotaHero): Double{
        return try{
            heroDetail.base_mana_regen!! + (heroDetail.base_int!! + heroDetail.int_gain!! *(heroLvl-1)) * 0.05
        }catch (e: Exception){
            75.0
        }
    }
    /** This function calculates Hero Base Armor according to Hero Level*/
    fun calculateMainArmor(heroLvl: Int, heroDetail: DotaHero): Double{
        val mainAgi = heroDetail.base_agi!! + heroDetail.agi_gain!! *(heroLvl-1)
        val oneSixth = Rational(1,6).toDouble()
        return try{
            heroDetail.base_armor!!.toDouble() + mainAgi * oneSixth
        }catch (e: Exception){
            0.0
        }
    }

    /** This function assign Attribute icon for the Hero's Primary Attribute*/
    fun getPrimaryAttr(hero: DotaHero): Int{
        return when (hero.primary_attr){
            "str" -> R.drawable.str_attribute_symbol
            "agi" -> R.drawable.agi_attribute_symbol
            "int" -> R.drawable.int_attribute_symbol
            else -> R.drawable.melee_icon
        }
    }

    /** This function saves Favorite Hero to a string set saved in Shared Preference*/
    fun saveFavoriteHeroes(hero: DotaHero){
        val favHeroes =  DotaHeroesApp.sharedPreferences?.getStringSet(Constants.PREF_FAVORITE_HEROES, mutableSetOf())
        if(favHeroes?.contains(hero.id.toString()) == false) favHeroes.add(hero.id.toString())
        DotaHeroesApp.sharedPreferences?.edit {
            remove(Constants.PREF_FAVORITE_HEROES)
            apply()
            putStringSet(Constants.PREF_FAVORITE_HEROES, favHeroes)
        }
    }

    /** This function deletes Favorite Hero to a string set saved in Shared Preference*/
    fun deleteFavoriteHeroes(hero: DotaHero){
        val favHeroes =  DotaHeroesApp.sharedPreferences?.getStringSet(Constants.PREF_FAVORITE_HEROES, mutableSetOf())
        if(favHeroes?.contains(hero.id.toString()) == true) favHeroes.remove(hero.id.toString())
        DotaHeroesApp.sharedPreferences?.edit {
            remove(Constants.PREF_FAVORITE_HEROES)
            apply()
            putStringSet(Constants.PREF_FAVORITE_HEROES, favHeroes)
        }
    }
}