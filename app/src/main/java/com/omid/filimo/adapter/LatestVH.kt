package com.omid.filimo.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class LatestVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val cardLatest = itemView.findViewById<CardView>(R.id.card_latest)!!
    val imgLatest = itemView.findViewById<AppCompatImageView>(R.id.img_latest)!!
    val txtLatest = itemView.findViewById<AppCompatTextView>(R.id.txt_latest)!!
}