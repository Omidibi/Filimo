package com.omid.filimo.ui.dashboard.showCase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.Category
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class CategoriesDashboardAdapter(private val list: List<Category>): RecyclerView.Adapter<CategoriesDashboardVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesDashboardVH {
        return CategoriesDashboardVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.category_dashboard_row,null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoriesDashboardVH, position: Int) {
        holder.apply {
            val catInfo = list[position]
            txtCategory.text = catInfo.categoryName
            Glide.with(AppConfiguration.getContext()).load(catInfo.categoryImageThumb).into(imgCategory)
        }
    }
}