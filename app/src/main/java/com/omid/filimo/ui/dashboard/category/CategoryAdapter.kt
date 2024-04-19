package com.omid.filimo.ui.dashboard.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.model.Category
import com.omid.filimo.utils.configuration.AppConfiguration

class CategoryAdapter(private val list: List<Category>,private val fragment: Fragment) : RecyclerView.Adapter<CategoryVH>() {

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.category_row, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.apply {
            val category = list[position]
            txtCatTitle.text = category.categoryName
            Glide.with(AppConfiguration.getContext()).load(category.categoryImageThumb).into(imgCategory)
            catCardView.setOnClickListener {
                bundle.putParcelable("category",category)
                fragment.findNavController().navigate(R.id.action_categoryFragment_to_videoListByCatIdFragment,bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}