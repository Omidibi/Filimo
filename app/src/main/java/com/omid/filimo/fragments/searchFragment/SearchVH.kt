package com.omid.filimo.fragments.searchFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class SearchVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvSearch = itemView.findViewById<CardView>(R.id.cv_search)!!
    val imgSearch = itemView.findViewById<AppCompatImageView>(R.id.img_search)!!
    val txtSearch = itemView.findViewById<AppCompatTextView>(R.id.txt_search)!!
}