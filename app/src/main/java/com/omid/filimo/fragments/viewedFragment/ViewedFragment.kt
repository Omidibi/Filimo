package com.omid.filimo.fragments.viewedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.filimo.databinding.FragmentViewedBinding

class ViewedFragment : Fragment() {

    private lateinit var binding: FragmentViewedBinding
    private lateinit var viewedViewModel: ViewedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()
        checkUi()
        checkLiveInternet()
        setupRvViewed()
    }

    override fun onResume() {
        super.onResume()
        checkNetwork()
    }

    private fun setupBinding() {
        binding = FragmentViewedBinding.inflate(layoutInflater)
        viewedViewModel = ViewModelProvider(requireActivity())[ViewedViewModel::class.java]
    }

    private fun checkNetwork(){
        binding.apply {
            if (viewedViewModel.networkAvailable()){
                rvViewed.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
            }else {
                rvViewed.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun checkUi(){
        binding.apply {
            if (viewedViewModel.isEmptyViewed()){
                emptyViewed.visibility = View.VISIBLE
                rvViewed.visibility = View.GONE
            }else{
                emptyViewed.visibility = View.GONE
                rvViewed.visibility = View.VISIBLE
            }
        }
    }

    private fun checkLiveInternet(){
        binding.apply {
            viewedViewModel.checkNetworkConnection.observe(viewLifecycleOwner){ isConnected->
                if (isConnected){
                    rvViewed.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                }else {
                    rvViewed.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRvViewed(){
        binding.apply {
            rvViewed.adapter = ViewedAdapter(viewedViewModel.showAllViewed(),this@ViewedFragment)
            rvViewed.layoutManager = GridLayoutManager(requireContext(),3)
        }
    }
}