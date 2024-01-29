package com.omid.filimo.ui.main.myFilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.databinding.FragmentMyfilmsBinding

class MyFilmsFragment : Fragment() {
    private lateinit var binding: FragmentMyfilmsBinding
    private val webServiceCaller = WebServiceCaller()
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        setupTabLayout()

        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentMyfilmsBinding.inflate(layoutInflater)
        binding.apply {

        }
    }

    private fun setupTabLayout(){
        binding.apply {
            tabLayout = tabMyFilms
            tabLayout.addTab(tabLayout.newTab().setText("نشان شده ها"))
            tabLayout.addTab(tabLayout.newTab().setText("مشاهده شده ها"))
            tabLayout.addTab(tabLayout.newTab().setText("گالری آفلاین"))
            tabLayout.tabGravity =TabLayout.GRAVITY_FILL
            vpTab.adapter = TabAdapter(tabLayout.tabCount,this@MyFilmsFragment)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
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
                    when(position){
                        0 -> tabLayout.getTabAt(position)?.select()
                        1 -> tabLayout.getTabAt(position)?.select()
                        2 -> tabLayout.getTabAt(position)?.select()
                    }
                }
            })
        }
    }
}