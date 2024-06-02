package com.dicoding.chownow.ui.dashboard.resto.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.chownow.R
import com.dicoding.chownow.databinding.ActivityRestoDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RestoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestoDetailBinding
    private val tabLayout: TabLayout by lazy { findViewById(R.id.tab_layout) }
    private val viewPager: ViewPager2 by lazy { findViewById(R.id.viewpager) }

    private val fragmentList = mutableListOf<Fragment>()
    private val tabTitleList = mutableListOf<String>()

    private lateinit var tabLayoutMediator : TabLayoutMediator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestoDetailBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_resto_detail)

        setupTabLayout()
        setupView()
    }

    private fun setupTabLayout(){
        tabTitleList.add("Menu")
        fragmentList.add(DetailMenuFragment.newInstance(0))

        tabTitleList.add("Ulasan")
        fragmentList.add(DetailReviewFragment.newInstance(1))

        tabTitleList.add("Tentang")
        fragmentList.add(DetailAboutFragment.newInstance(2))
    }

    private fun setupView() {
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = TabLayoutAdapter(this, fragmentList)

        tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleList[position] }

        tabLayoutMediator.attach()
    }

    class TabLayoutAdapter(
        fragmentActivity: FragmentActivity,
        private val fragmentList: MutableList<Fragment>
    ): FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

    }
}