package com.omid.filimo.ui.dashboard.myFilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.databinding.FragmentMyfilmsBinding

class MyFilmsFragment : Fragment() {

    private lateinit var binding: FragmentMyfilmsBinding
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkUiStatus()
        setupTabLayout()
        clickEvents()
    }

    private fun setupBinding() {
        binding = FragmentMyfilmsBinding.inflate(layoutInflater)
    }

    private fun checkUiStatus() {
        if (MainWidget.bnv.visibility == View.GONE) {
            MainWidget.bnv.visibility = View.VISIBLE
        }
        if (MainWidget.toolbar.visibility == View.GONE) {
            MainWidget.toolbar.visibility = View.VISIBLE
        }
    }

    private fun setupTabLayout() {
        binding.apply {
            tabLayout = tabMyFilms
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.bookmark)))
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.viewed)))
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.offline_gallery)))
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            vpTab.adapter = TabAdapter(tabLayout.tabCount, this@MyFilmsFragment)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    vpTab.currentItem = tab!!.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

            vpTab.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> tabLayout.getTabAt(position)?.select()
                        1 -> tabLayout.getTabAt(position)?.select()
                        2 -> tabLayout.getTabAt(position)?.select()
                    }
                }
            })
        }
    }

    private fun clickEvents() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { }
    }
}