package com.omid.filimo.ui.dashboard.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omid.filimo.databinding.FragmentCategoryBinding
import com.omid.filimo.utils.progressBarStatus.ProgressBarStatus

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()
        categoryObserver()
        progressBarStatus()
    }

    private fun setupBinding() {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        categoryViewModel = ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
    }

    private fun checkNetwork(){
        binding.apply {
            if (categoryViewModel.networkAvailable()) {
                rvCategory.visibility = View.VISIBLE
                pbCategory.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
            } else {
                rvCategory.visibility = View.GONE
                pbCategory.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun categoryObserver(){
        binding.apply {
            if (isAdded){
                categoryViewModel.checkNetworkConnection.observe(viewLifecycleOwner){ isConnect->
                    pbCategory.visibility = View.VISIBLE
                    rvCategory.visibility = View.GONE
                    liveNoConnection.visibility = View.GONE
                    if (isConnect){
                        categoryViewModel.categoryModel.observe(viewLifecycleOwner){ categoryModel->
                            pbCategory.visibility = View.GONE
                            rvCategory.visibility = View.VISIBLE
                            liveNoConnection.visibility = View.GONE
                            rvCategory.adapter = categoryModel?.category?.let { CategoryAdapter(it,this@CategoryFragment) }
                            rvCategory.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                        }
                    }else {
                        pbCategory.visibility = View.GONE
                        rvCategory.visibility = View.GONE
                        liveNoConnection.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun progressBarStatus() {
        ProgressBarStatus.pbStatus(binding.pbCategory)
    }
}