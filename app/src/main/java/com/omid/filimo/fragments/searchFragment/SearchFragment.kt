package com.omid.filimo.fragments.searchFragment

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
import com.omid.filimo.databinding.FragmentSearchBinding
import com.omid.filimo.utils.ProgressBarStatus

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchViewModel: SearchViewModel
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
        searchObserver()
        searchViewStatus()
        clickEvents()
        progressBarStatus()
    }

    private fun progressBarStatus() {
        ProgressBarStatus.pbStatus(binding.pbSearch)
    }

    private fun checkNetwork(){
        binding.apply {
            if (searchViewModel.networkAvailable()){
                rvSearch.visibility = View.VISIBLE
                mcv.visibility = View.VISIBLE
                pbSearch.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
            }else {
                rvSearch.visibility = View.GONE
                mcv.visibility = View.GONE
                pbSearch.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun setupBinding(){
        binding = FragmentSearchBinding.inflate(layoutInflater)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    private fun searchViewStatus(){
        binding.apply {
            sv.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { it ->
                        searchViewModel.checkNetworkConnection.observe(owner){ isConnect->
                            if (!isConnect){
                                sv.setQuery("",false)
                            }
                        }
                        if (it.isEmpty()){
                            rvSearch.visibility = View.GONE
                        } else {
                            searchViewModel.getSearchVideo(newText).observe(owner){ searchModel->
                                rvSearch.visibility = View.VISIBLE
                                mcv.visibility = View.VISIBLE
                                pbSearch.visibility = View.GONE
                                liveNoConnection.visibility = View.GONE
                                rvSearch.adapter = searchModel?.search?.let { SearchAdapter(it) }
                                rvSearch.layoutManager = GridLayoutManager(requireContext(),3)
                            }
                        }
                    }
                    return true
                }

            })

        }
    }

    private fun searchObserver(){
        binding.apply {
            searchViewModel.checkNetworkConnection.observe(owner){ isConnect->
                if (isConnect){
                    rvSearch.visibility = View.GONE
                    mcv.visibility = View.VISIBLE
                    pbSearch.visibility = View.GONE
                    liveNoConnection.visibility = View.GONE
                }else {
                    rvSearch.visibility = View.GONE
                    mcv.visibility = View.GONE
                    pbSearch.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun clickEvents(){
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                findNavController().popBackStack()
                MainWidget.bnv.visibility = View.VISIBLE
                MainWidget.toolbar.visibility = View.VISIBLE
            }

            imgBack.setOnClickListener {
                findNavController().popBackStack()
                MainWidget.bnv.visibility = View.VISIBLE
                MainWidget.toolbar.visibility = View.VISIBLE
            }
        }
    }
}