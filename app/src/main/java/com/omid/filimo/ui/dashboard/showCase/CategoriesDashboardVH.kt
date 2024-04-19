package com.omid.filimo.ui.dashboard.showCase

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class CategoriesDashboardVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvCategory = itemView.findViewById<CardView>(R.id.cv_category)!!
    val imgCategory = itemView.findViewById<AppCompatImageView>(R.id.img_category)!!
    val txtCategory = itemView.findViewById<AppCompatTextView>(R.id.txt_category)!!
}