package com.plentina.codingchallengeplentinajcalma.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DotaHero(
    var id: Int? = null,
    var name: String? = null,
    var localized_name: String? = null,
    var primary_attr: String? = null,
    var attack_type: String? = null,
    var roles: List<String>? = null,
    var img: String? = null,
    var icon: String? = null,
    var base_health: Int? = null,
    var base_health_regen: Double? = null,
    var base_mana: Int? = null,
    var base_mana_regen: Double? = null,
    var base_armor: Double? = null,
    var base_mr: Double? = null,
    var base_attack_min: Int? = null,
    var base_attack_max: Int? = null,
    var base_str: Int? = null,
    var base_agi: Int? = null,
    var base_int: Int? = null,
    var str_gain: Double? = null,
    var agi_gain: Double? = null,
    var int_gain: Double? = null,
    var attack_range: Int? = null,
    var projectile_speed: Int? = null,
    var attack_rate: Double? = null,
    var move_speed: Int? = null,
    var legs: Int? = null,
    var hero_id: Int? = null
): Parcelable {

}
