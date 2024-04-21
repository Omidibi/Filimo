package com.omid.filimo.ui.dashboard.showCase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.databinding.FragmentShowcaseBinding
import com.omid.filimo.ui.customView.customUI.CustomUI
import com.omid.filimo.utils.progressBarStatus.ProgressBarStatus
import java.util.Timer
import java.util.TimerTask

class ShowCaseFragment : Fragment() {

    private lateinit var binding: FragmentShowcaseBinding
    private lateinit var showCaseViewModel: ShowCaseViewModel
    private var currentPage = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()
        homeObserver()
        progressBarStatus()
        setupPagerBanner()
        srlStatus()
        clickEvents()
    }

    private fun setupBinding(){
        binding = FragmentShowcaseBinding.inflate(layoutInflater)
        showCaseViewModel = ViewModelProvider(requireActivity())[ShowCaseViewModel::class.java]
    }

    private fun progressBarStatus() {
        ProgressBarStatus.pbStatus(binding.pbShowCase)
    }

    private fun checkNetwork(){
        binding.apply {
            if (showCaseViewModel.networkAvailable()) {
                srl.visibility = View.VISIBLE
                pbShowCase.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
            } else {
                srl.visibility = View.GONE
                pbShowCase.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
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

    private fun homeObserver(){
        binding.apply {
            if (isAdded){
                showCaseViewModel.checkNetworkConnection.observe(viewLifecycleOwner){ isConnect->
                    pbShowCase.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    liveNoConnection.visibility = View.GONE
                    if (isConnect){
                        showCaseViewModel.banner.observe(viewLifecycleOwner){ bannerModel->
                            pbShowCase.visibility = View.GONE
                            srl.visibility = View.VISIBLE
                            liveNoConnection.visibility = View.GONE
                            pagerBanner.adapter = bannerModel?.banner?.let { BannerAdapter(it) }
                        }
                        showCaseViewModel.homeVideos.observe(viewLifecycleOwner){ homeVideo->
                            pbShowCase.visibility = View.GONE
                            srl.visibility = View.VISIBLE
                            liveNoConnection.visibility = View.GONE
                            rvFeaturedVideo.adapter = homeVideo?.allInOneVideo?.featuredVideo?.let { FeaturedVideoAdapter(it,this@ShowCaseFragment) }
                            rvFeaturedVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                            rvLatestVideo.adapter = homeVideo?.allInOneVideo?.latestVideo?.let { LatestVideoAdapter(it,this@ShowCaseFragment) }
                            rvLatestVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                            rvAllVideo.adapter = homeVideo?.allInOneVideo?.video?.let { AllVideoAdapter(it,this@ShowCaseFragment) }
                            rvAllVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                            rvCategories.adapter = homeVideo?.allInOneVideo?.category?.let { CategoriesDashboardAdapter(it,this@ShowCaseFragment) }
                            rvCategories.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                        }
                    }else {
                        pbShowCase.visibility = View.GONE
                        srl.visibility = View.GONE
                        liveNoConnection.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun srlStatus(){
        binding.apply {
            srl.setOnRefreshListener {
                pbShowCase.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
                srl.visibility = View.GONE
                showCaseViewModel.getBanner()
                showCaseViewModel.getHomeVideos()
                currentPage = 0
                srl.isRefreshing = false
            }
        }
    }

    private fun clickEvents(){
        binding.apply {
            moreAll.setOnClickListener {
                findNavController().navigate(R.id.action_showCaseFragment_to_allVideoFragment)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }

            moreLatest.setOnClickListener {
                findNavController().navigate(R.id.action_showCaseFragment_to_latestVideoFragment)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }

            moreCategories.setOnClickListener {
                findNavController().navigate(R.id.action_showCaseFragment_to_categoryFragment)
                MainWidget.bnv.menu.findItem(R.id.item_category).isChecked = true
                CustomUI.changeStatusOnClickEvent(MainWidget.bnv)
            }
        }
    }
}