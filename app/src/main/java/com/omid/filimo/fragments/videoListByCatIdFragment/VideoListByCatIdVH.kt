package com.omid.filimo.fragments.videoListByCatIdFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class VideoListByCatIdVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvCatById = itemView.findViewById<CardView>(R.id.cv_cat_by_id)!!
    val imgCatById = itemView.findViewById<AppCompatImageView>(R.id.img_cat_by_id)!!
    val txtCatById = itemView.findViewById<AppCompatTextView>(R.id.txt_cat_by_id)!!
}