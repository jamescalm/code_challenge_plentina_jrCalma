package com.plentina.codingchallengeplentinajcalma.ui.heroListFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.plentina.codingchallengeplentinajcalma.R
import com.plentina.codingchallengeplentinajcalma.ViewModelFactory
import com.plentina.codingchallengeplentinajcalma.api.HeroesHttp
import com.plentina.codingchallengeplentinajcalma.databinding.FragmentHeroListBinding
import com.plentina.codingchallengeplentinajcalma.model.DotaHero
import com.plentina.codingchallengeplentinajcalma.ui.heroListFragment.heroAdapter.HeroAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class HeroListFragment : Fragment(), HeroAdapter.OnHeroClickListener {
    private lateinit var binding: FragmentHeroListBinding

    @Inject
    lateinit var api: HeroesHttp

    lateinit var factory: ViewModelFactory

    lateinit var viewModel: HeroListViewModel

    private var strAdapter: HeroAdapter? = null
    private var agiAdapter: HeroAdapter? = null
    private var intAdapter: HeroAdapter? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        factory = ViewModelFactory(api)
        viewModel = ViewModelProvider(this, factory).get(HeroListViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHeroListBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.strRv.layoutManager = GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false )
        binding.agiRv.layoutManager = GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false )
        binding.intRv.layoutManager = GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false )
        viewModel.getHeroList()
        setupObservers()
    }
    override fun onResume() {
        super.onResume()
        binding.strRv.adapter = strAdapter
        binding.agiRv.adapter = agiAdapter
        binding.intRv.adapter = intAdapter
    }

    private fun setupObservers(){
        viewModel.heroList.observe(viewLifecycleOwner, {
            if (it != null) {
                initStrAdapter(it)
                initAgiAdapter(it)
                initIntAdapter(it)
            }
        })
    }

    private fun initStrAdapter(heroes: List<DotaHero>){
        val strHeroes = heroes.filter {
            it.primary_attr == "str"
        }
        if(strAdapter == null) {
            strAdapter = context?.let { HeroAdapter(it, strHeroes, this) }
            binding.strRv.adapter = strAdapter
        } else {
            strAdapter?.heroList = heroes
            strAdapter?.notifyDataSetChanged()
        }
    }

    private fun initAgiAdapter(heroes: List<DotaHero>){
        val agiHeroes = heroes.filter {
            it.primary_attr == "agi"
        }
        if(agiAdapter == null) {
            agiAdapter = context?.let { HeroAdapter(it, agiHeroes, this) }
            binding.agiRv.adapter = agiAdapter
        } else {
            agiAdapter?.heroList = heroes
            agiAdapter?.notifyDataSetChanged()
        }
    }

    private fun initIntAdapter(heroes: List<DotaHero>){
        val intHeroes = heroes.filter {
            it.primary_attr == "int"
        }
        if(intAdapter == null) {
            intAdapter = context?.let { HeroAdapter(it, intHeroes, this) }
            binding.intRv.adapter = intAdapter
        } else {
            intAdapter?.heroList = heroes
            intAdapter?.notifyDataSetChanged()
        }
    }

    override fun onHeroClicked(hero: DotaHero) {
        TODO("Not yet implemented")
    }
}