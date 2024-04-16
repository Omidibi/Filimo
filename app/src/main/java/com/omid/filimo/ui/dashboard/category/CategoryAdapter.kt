package com.omid.filimo.ui.dashboard.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R
import com.omid.filimo.model.Category
import com.omid.filimo.utils.configuration.AppConfiguration

class CategoryAdapter(private val list: List<Category>) : RecyclerView.Adapter<CategoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.category_row, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category = list[position]

    }
}