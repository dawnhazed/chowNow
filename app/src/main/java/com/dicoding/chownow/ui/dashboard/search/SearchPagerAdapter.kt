package com.dicoding.chownow.ui.dashboard.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2 // Jumlah tab (Search Resto dan Search Menu)

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchRestoFragment()
            1 -> SearchMenuFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}