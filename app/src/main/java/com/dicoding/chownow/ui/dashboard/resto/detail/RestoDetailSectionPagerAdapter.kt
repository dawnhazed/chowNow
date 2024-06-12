package com.dicoding.chownow.ui.dashboard.resto.detail

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.chownow.R


class RestoDetailSectionPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    @StringRes
    val tabTitles = arrayOf(R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3)

    override fun getItemCount(): Int = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailMenuFragment()
            1 -> DetailReviewFragment()
            2 -> DetailAboutFragment()
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}