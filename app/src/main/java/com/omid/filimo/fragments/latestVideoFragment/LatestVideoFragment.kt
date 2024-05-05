package com.omid.filimo.fragments.latestVideoFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.databinding.FragmentLatestVideoBinding
import com.omid.filimo.utils.progressBarStatus.ProgressBarStatus

class LatestVideoFragment : Fragment() {

    private lateinit var binding: FragmentLatestVideoBinding
    private lateinit var latestVideoViewModel: LatestVideoViewModel
    private lateinit var owner: LifecycleOwner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()
        checkStatusUI()
        latestVideoObserver()
        srlStatus()
        progressBarStatus()
        clickEvents()
    }

    private fun setupBinding() {
        binding = FragmentLatestVideoBinding.inflate(layoutInflater)
        latestVideoViewModel = ViewModelProvider(this)[LatestVideoViewModel::class.java]
    }

    private fun checkStatusUI() {
        binding.apply {
            if (MainWidget.bnv.visibility == View.VISIBLE) {
                MainWidget.bnv.visibility = View.GONE
            }
            if (MainWidget.toolbar.visibility == View.VISIBLE) {
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }

    private fun progressBarStatus() {
        ProgressBarStatus.pbStatus(binding.pbLatestVideo)
    }

    private fun checkNetwork() {
        binding.apply {
            if (latestVideoViewModel.networkAvailable()) {
                srl.visibility = View.VISIBLE
                pbLatestVideo.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
            } else {
                srl.visibility = View.GONE
                pbLatestVideo.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun latestVideoObserver() {
        binding.apply {
            if (isAdded) {
                latestVideoViewModel.checkNetworkConnection.observe(owner) { isConnect ->
                    pbLatestVideo.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    liveNoConnection.visibility = View.GONE
                    if (isConnect) {
                        latestVideoViewModel.latestVideoModel.observe(owner) { latestVideoModel ->
                            pbLatestVideo.visibility = View.GONE
                            srl.visibility = View.VISIBLE
                            liveNoConnection.visibility = View.GONE
                            rvLatestVideo.adapter = latestVideoModel?.latestVideo?.let { LatestVideoListAdapter(it, this@LatestVideoFragment) }
                            rvLatestVideo.layoutManager = GridLayoutManager(requireContext(), 3)
                        }
                    } else {
                        pbLatestVideo.visibility = View.GONE
                        srl.visibility = View.GONE
                        liveNoConnection.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun srlStatus() {
        binding.apply {
            srl.setOnRefreshListener {
                pbLatestVideo.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
                srl.visibility = View.GONE
                latestVideoViewModel.getLatestVideo()
                srl.isRefreshing = false
            }
        }
    }

    private fun clickEvents() {
        binding.apply {

            imgBack.setOnClickListener {
                findNavController().popBackStack()
                MainWidget.bnv.visibility = View.VISIBLE
                MainWidget.toolbar.visibility = View.VISIBLE
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                findNavController().popBackStack()
                MainWidget.bnv.visibility = View.VISIBLE
                MainWidget.toolbar.visibility = View.VISIBLE
            }
        }
    }
}