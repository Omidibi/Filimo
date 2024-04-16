package com.omid.filimo.ui.dashboard.showCase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.omid.filimo.databinding.FragmentShowcaseBinding
import java.util.Timer
import java.util.TimerTask

class ShowCaseFragment : Fragment() {

    private lateinit var binding: FragmentShowcaseBinding
    private var currentPage = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPagerBanner()
    }

    private fun setupBinding(){
        binding = FragmentShowcaseBinding.inflate(layoutInflater)
    }

    private fun setupPagerBanner(){
        binding.apply {
            val handler = Handler(Looper.getMainLooper())
            val update = Runnable {
                if (currentPage == (pagerBanner.adapter?.count ?: 0)){
                    currentPage = 0
                }
                pagerBanner.setCurrentItem(currentPage++,true)
            }

            Timer().schedule(object : TimerTask(){ override fun run() { handler.post(update) } },3000,3000)

            pagerBanner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    currentPage = position
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })
        }
    }
}