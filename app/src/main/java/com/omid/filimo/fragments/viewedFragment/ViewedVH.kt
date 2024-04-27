package com.omid.filimo.fragments.viewedFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class ViewedVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvViewed = itemView.findViewById<CardView>(R.id.cv_viewed)!!
    val imgViewed = itemView.findViewById<AppCompatImageView>(R.id.img_viewed)!!
    val txtViewed = itemView.findViewById<AppCompatTextView>(R.id.txt_viewed)!!
}