package com.plentina.codingchallengeplentinajcalma.ui.heroDetail.roleAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.plentina.codingchallengeplentinajcalma.R
import com.plentina.codingchallengeplentinajcalma.databinding.AdapterRoleBinding

class RoleAdapter (
        var context: Context,
        var roles: List<String>,
) : androidx.recyclerview.widget.RecyclerView.Adapter<RoleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoleAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterRoleBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoleAdapter.ViewHolder, position: Int) {
        val role = roles[position]
        holder.bindRole(role)
    }

    override fun getItemCount(): Int {
        return roles.size
    }

    inner class ViewHolder(val binding: AdapterRoleBinding): androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bindRole(role: String) {
            with(binding){
                imgRole.setImageDrawable(context.getDrawable(getRoleIcon(role)))
                txtRole.text = role
            }
        }
    }
    /** This function sets up the Role Icon for a given role*/
    fun getRoleIcon(role: String): Int{
        return when(role){
            "Carry"-> R.drawable.ic_carry
            "Support"-> R.drawable.ic_support
            "Nuker"-> R.drawable.ic_nuker
            "Disabler"-> R.drawable.ic_disabler
            "Jungler"-> R.drawable.ic_jungler
            "Durable"-> R.drawable.ic_durable
            "Escape"-> R.drawable.ic_escape
            "Pusher"-> R.drawable.ic_pusher
            "Initiator"-> R.drawable.ic_initiator
            else-> R.drawable.ic_support
        }
    }
}
