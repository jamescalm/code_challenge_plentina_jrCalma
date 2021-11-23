package com.plentina.codingchallengeplentinajcalma.ui.heroListFragment.heroAdapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mcxiaoke.koi.ext.getWindowService
import com.plentina.codingchallengeplentinajcalma.R
import com.plentina.codingchallengeplentinajcalma.databinding.AdapterHeroBinding
import com.plentina.codingchallengeplentinajcalma.extensions.loadItemImage
import com.plentina.codingchallengeplentinajcalma.model.Constants
import com.plentina.codingchallengeplentinajcalma.model.DotaHero


class HeroAdapter(
        var context: Context,
        var heroList: List<DotaHero>,
        var onHeroClickListener: OnHeroClickListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    interface OnHeroClickListener{
        fun onHeroClicked(hero: DotaHero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterHeroBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return heroList.size
    }
    override fun onBindViewHolder(holder: HeroAdapter.ViewHolder, position: Int) {
        val track = heroList[position]
        holder.bindTrack(track)
    }

    inner class ViewHolder(val binding: AdapterHeroBinding): androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bindTrack(hero: DotaHero) {
            with(binding) {
                val displayMetrics = DisplayMetrics()
                context.getWindowService().defaultDisplay.getRealMetrics(displayMetrics)
                var width = displayMetrics.widthPixels
                var quarterWidth = width * 0.25
                imgHero.layoutParams.width = quarterWidth.toInt() - 5
                val imgURL = Constants.base_url + hero.img
                context.getDrawable(R.drawable.dark_img_placeholder)?.let { imgHero.loadItemImage(imgURL, it) }
                txtHeroName.text = hero.localized_name
            }
        }
    }
}