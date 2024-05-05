package com.omid.filimo.ui.dashboard.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.model.Category
import com.omid.filimo.utils.configuration.AppConfiguration

class CategoryAdapter(private val list: List<Category>, private val fragment: Fragment) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {

    inner class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val catCardView = itemView.findViewById<CardView>(R.id.cat_card_view)!!
        val imgCategory = itemView.findViewById<AppCompatImageView>(R.id.img_category)!!
        val txtCatTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_cat_title)!!
    }

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
                bundle.putParcelable("category", category)
                fragment.findNavController().navigate(R.id.action_categoryFragment_to_videoListByCatIdFragment, bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}