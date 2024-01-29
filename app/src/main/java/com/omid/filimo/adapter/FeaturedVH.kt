package com.omid.filimo.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class FeaturedVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val cardFeatured = itemView.findViewById<CardView>(R.id.card_featured)!!
    val imgFeatured = itemView.findViewById<AppCompatImageView>(R.id.img_featured)!!
    val txtFeatured = itemView.findViewById<AppCompatTextView>(R.id.txt_featured)!!
}