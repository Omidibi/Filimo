package com.omid.filimo.ui.main.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.databinding.FragmentCategoryBinding
import com.omid.filimo.model.listener.IListener
import com.omid.filimo.model.models.CategoryModel
import retrofit2.Call

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private val webServiceCaller = WebServiceCaller()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()

        return binding.root
    }

    private fun setupBinding() {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        binding.apply {

        }
    }

    private fun categories() {
        binding.apply {
            webServiceCaller.getCategories(object : IListener<CategoryModel> {
                override fun onSuccess(call: Call<CategoryModel>, response: CategoryModel) {
                    Log.e("", "")
                }

                override fun onFailure(call: Call<CategoryModel>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                }

            })
        }
    }
}