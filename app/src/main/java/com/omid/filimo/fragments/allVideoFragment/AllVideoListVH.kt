package com.omid.filimo.fragments.allVideoFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class AllVideoListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvAll = itemView.findViewById<CardView>(R.id.cv_all)!!
    val imgAll = itemView.findViewById<AppCompatImageView>(R.id.img_all)!!
    val txtAll = itemView.findViewById<AppCompatTextView>(R.id.txt_all)!!
}