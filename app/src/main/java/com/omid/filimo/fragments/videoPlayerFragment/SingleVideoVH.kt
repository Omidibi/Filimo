package com.omid.filimo.fragments.videoPlayerFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class SingleVideoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvSingleVideo = itemView.findViewById<CardView>(R.id.cv_single_video)!!
    val imgSingleVideo = itemView.findViewById<AppCompatImageView>(R.id.img_single_video)!!
    val txtSingleVideo = itemView.findViewById<AppCompatTextView>(R.id.txt_single_video)!!
}