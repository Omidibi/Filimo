package com.omid.filimo.ui.dashboard.showCase

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class AllVideoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_all_video)!!
    val imgVideo = itemView.findViewById<AppCompatImageView>(R.id.img_all_video)!!
    val cvAllVideo = itemView.findViewById<CardView>(R.id.cv_all_video)!!
}