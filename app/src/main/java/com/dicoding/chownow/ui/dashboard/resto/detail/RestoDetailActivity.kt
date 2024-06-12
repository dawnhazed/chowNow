package com.dicoding.chownow.ui.dashboard.resto.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.chownow.R
import com.dicoding.chownow.databinding.ActivityRestoDetailBinding
import com.google.android.material.tabs.TabLayoutMediator


class RestoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestoDetailBinding

    //private lateinit var tabLayoutMediator : TabLayoutMediator

    companion object {
        const val EXTRA_RESTO = "extra_resto"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_RESTO).orEmpty()

        val bundle = Bundle().apply {
            putString(EXTRA_RESTO, username)
        }

        val sectionPagerAdapter = RestoDetailSectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(sectionPagerAdapter.tabTitles[position])
        }.attach()
    }

//    class TabLayoutAdapter(
//        fragmentActivity: FragmentActivity,
//        private val fragmentList: MutableList<Fragment>
//    ): FragmentStateAdapter(fragmentActivity) {
//        override fun getItemCount(): Int {
//            return fragmentList.size
//        }
//
//        override fun createFragment(position: Int): Fragment {
//            return fragmentList[position]
//        }
//
//    }
}