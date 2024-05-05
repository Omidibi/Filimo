package com.omid.filimo.ui.dashboard.showCase

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

class CategoriesDashboardAdapter(private val list: List<Category>, private val fragment: Fragment) : RecyclerView.Adapter<CategoriesDashboardAdapter.CategoriesDashboardVH>() {

    inner class CategoriesDashboardVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCategory = itemView.findViewById<CardView>(R.id.cv_category)!!
        val imgCategory = itemView.findViewById<AppCompatImageView>(R.id.img_category)!!
        val txtCategory = itemView.findViewById<AppCompatTextView>(R.id.txt_category)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesDashboardVH {
        return CategoriesDashboardVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.category_dashboard_row, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoriesDashboardVH, position: Int) {
        holder.apply {
            val catInfo = list[position]
            txtCategory.text = catInfo.categoryName
            Glide.with(AppConfiguration.getContext()).load(catInfo.categoryImageThumb).into(imgCategory)
            cvCategory.setOnClickListener {
                bundle.putParcelable("category", catInfo)
                fragment.findNavController().navigate(R.id.action_showCaseFragment_to_videoListByCatIdFragment, bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}