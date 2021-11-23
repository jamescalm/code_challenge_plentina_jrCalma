package com.plentina.codingchallengeplentinajcalma.ui.heroDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.plentina.codingchallengeplentinajcalma.R
import com.plentina.codingchallengeplentinajcalma.databinding.FragmentHeroDetailBinding


class HeroDetailFragment : Fragment() {
    lateinit var binding: FragmentHeroDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeroDetailBinding.inflate(layoutInflater)
        return binding.root
    }
}