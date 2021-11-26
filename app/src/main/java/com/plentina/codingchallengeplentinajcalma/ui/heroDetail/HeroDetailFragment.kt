package com.plentina.codingchallengeplentinajcalma.ui.heroDetail

import android.content.Context
import android.os.Bundle
import android.text.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mcxiaoke.koi.ext.toast
import com.plentina.codingchallengeplentinajcalma.DotaHeroesApp
import com.plentina.codingchallengeplentinajcalma.R
import com.plentina.codingchallengeplentinajcalma.ViewModelFactory
import com.plentina.codingchallengeplentinajcalma.api.HeroesHttp
import com.plentina.codingchallengeplentinajcalma.databinding.FragmentHeroDetailBinding
import com.plentina.codingchallengeplentinajcalma.extensions.loadItemImage
import com.plentina.codingchallengeplentinajcalma.extensions.toTwoDecStringDouble
import com.plentina.codingchallengeplentinajcalma.model.Constants
import com.plentina.codingchallengeplentinajcalma.model.DotaHero
import com.plentina.codingchallengeplentinajcalma.ui.heroDetail.roleAdapter.RoleAdapter
import com.plentina.codingchallengeplentinajcalma.util.InputFilterMinMax
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class HeroDetailFragment : Fragment() {
    lateinit var binding: FragmentHeroDetailBinding
    private val args: HeroDetailFragmentArgs by navArgs()

    @Inject
    lateinit var api: HeroesHttp

    lateinit var factory: ViewModelFactory

    lateinit var viewModel: HeroDetailViewModel

    private var roleAdapter: RoleAdapter? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        factory = ViewModelFactory(api)
        viewModel = ViewModelProvider(this, factory).get(HeroDetailViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeroDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            edtLevel.filters = arrayOf<InputFilter>(InputFilterMinMax("1", "30"))
            edtLevel.setText("1")
            edtLevel.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if(edtLevel.text.isEmpty()) {
                        edtLevel.setText("1")
                        viewModel.setLevel(1)
                    } else viewModel.setLevel(edtLevel.text.toString().toInt())
                    edtLevel.clearFocus()
                    true
                }
                false
            }
            btnAddFavHero.setOnClickListener {
                viewModel.saveFavoriteHeroes(args.hero)
                val message = SpannableStringBuilder()
                        .bold { context?.let { it1 -> color(it1.getColor(R.color.dark_green)){append(args.hero.localized_name)} } }
                        .append(" has been added to Favorite Heroes")
                toast(message)
                showAddDeleteBtn(args.hero)
            }
            btnDeleteFavHero.setOnClickListener {
                viewModel.deleteFavoriteHeroes(args.hero)
                val message = SpannableStringBuilder()
                        .bold { context?.let { it1 -> color(it1.getColor(R.color.dark_red)){append(args.hero.localized_name)} } }
                        .append(" has been removed from Favorite Heroes")
                toast(message)
                showAddDeleteBtn(args.hero)
            }
        }
        setupObservers()
        setupHeroDetails(1)
        args.hero.roles?.let { initRoleAdapter(it) }
    }

    private fun setupObservers(){
        viewModel.heroLevel.observe(viewLifecycleOwner, {
            setupHeroDetails(it)
        })
    }
    private fun setupHeroDetails(lvl: Int){
        val hero = args.hero
        with(binding){
            context?.getDrawable(R.drawable.dark_img_placeholder)?.let { imgHero.loadItemImage(Constants.base_url + hero.img, it) }
            context?.getDrawable(R.drawable.dark_img_placeholder)?.let { imgHeroIcon.loadItemImage(Constants.base_url + hero.icon, it) }
            context?.getDrawable(viewModel.getPrimaryAttr(hero))?.let { imgPrimaryAttr.setImageDrawable(it) }
            txtHeroName.text = hero.localized_name
            txtPrimaryAttr.text = hero.getPrimaryAttr()
            txtHP.text = viewModel.calculateHP(lvl, hero).toString()
            txtHPRegen.text = viewModel.calculateHPRegen(lvl, hero).toTwoDecStringDouble()
            txtMP.text = viewModel.calculateMP(lvl, hero).toString()
            txtMPRegen.text = viewModel.calculateMPRegen(lvl, hero).toTwoDecStringDouble()
            txtStr.text = attrGain(hero.base_str ?: 0, hero.str_gain ?: 0.0)
            txtAgi.text = attrGain(hero.base_agi ?: 0, hero.agi_gain ?: 0.0)
            txtInt.text = attrGain(hero.base_int ?: 0, hero.int_gain ?: 0.0)
            if(hero.attack_type == "Ranged") imgAttackType.setImageDrawable(context?.getDrawable(R.drawable.ranged_icon))
            else imgAttackType.setImageDrawable(context?.getDrawable(R.drawable.melee_icon))
            txtDamage.text = "${viewModel.calculateMinAttack(lvl, hero)} - ${viewModel.calculateMaxAttack(lvl, hero)}"
            txtBAT.text = hero.attack_rate?.toTwoDecStringDouble()
            txtAttackRange.text = hero.attack_range.toString()
            txtProjectileSpeed.text = hero.getProjectileSpeed()
            txtArmor.text = viewModel.calculateMainArmor(lvl, hero).toTwoDecStringDouble()
            txtMagicResist.text = "${hero.base_mr} %"
            txtMS.text = hero.move_speed.toString()
        }
        showAddDeleteBtn(hero)
    }

    fun showAddDeleteBtn(hero: DotaHero){
        val favHeroes =  DotaHeroesApp.sharedPreferences?.getStringSet(Constants.PREF_FAVORITE_HEROES, mutableSetOf())
        if(favHeroes?.contains(hero.id.toString()) == true) {
            binding.btnAddFavHero.isGone = true
            binding.btnDeleteFavHero.isVisible = true
        }else{
            binding.btnAddFavHero.isVisible = true
            binding.btnDeleteFavHero.isGone = true
        }

    }

    private fun attrGain(base: Int, gain: Double): SpannableStringBuilder{
        return SpannableStringBuilder()
                .bold { append(base.toString()) }
                .append(" + $gain")

    }

    private fun initRoleAdapter(roles: List<String>){
        if(roleAdapter == null) {
            roleAdapter = context?.let { RoleAdapter(it, roles) }
            binding.rolesRV.adapter = roleAdapter
        } else {
            roleAdapter?.roles = roles
            roleAdapter?.notifyDataSetChanged()
        }
    }
}