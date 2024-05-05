package com.omid.filimo.fragments.allVideoFragment

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
import com.omid.filimo.databinding.FragmentAllVideoBinding
import com.omid.filimo.utils.progressBarStatus.ProgressBarStatus

class AllVideoFragment : Fragment() {

    private lateinit var binding: FragmentAllVideoBinding
    private lateinit var allVideoViewModel: AllVideoViewModel
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
        allVideoObserver()
        srlStatus()
        progressBarStatus()
        clickEvents()
    }

    private fun setupBinding() {
        binding = FragmentAllVideoBinding.inflate(layoutInflater)
        allVideoViewModel = ViewModelProvider(this)[AllVideoViewModel::class.java]
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
        ProgressBarStatus.pbStatus(binding.pbAllVideo)
    }

    private fun checkNetwork() {
        binding.apply {
            if (allVideoViewModel.networkAvailable()) {
                srl.visibility = View.VISIBLE
                pbAllVideo.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
            } else {
                srl.visibility = View.GONE
                pbAllVideo.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun allVideoObserver() {
        binding.apply {
            if (isAdded) {
                allVideoViewModel.checkNetworkConnection.observe(owner) { isConnect ->
                    pbAllVideo.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    liveNoConnection.visibility = View.GONE
                    if (isConnect) {
                        allVideoViewModel.allVideoModel.observe(owner) { allVideoModel ->
                            pbAllVideo.visibility = View.GONE
                            srl.visibility = View.VISIBLE
                            liveNoConnection.visibility = View.GONE
                            rvAllVideo.adapter = allVideoModel?.allVideo?.let { AllVideoListAdapter(it, this@AllVideoFragment) }
                            rvAllVideo.layoutManager = GridLayoutManager(requireContext(), 3)
                        }
                    } else {
                        pbAllVideo.visibility = View.GONE
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
                pbAllVideo.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
                srl.visibility = View.GONE
                allVideoViewModel.getAllVideo()
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