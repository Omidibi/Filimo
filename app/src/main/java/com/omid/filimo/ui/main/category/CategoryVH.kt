package com.omid.filimo.ui.main.category

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val catCardView = itemView.findViewById<CardView>(R.id.cat_card_view)!!
    val imgCategory = itemView.findViewById<AppCompatImageView>(R.id.img_category)!!
    val txtCatTitle = itemView.findViewById<AppCompatTextView>(R.id.txt_cat_title)!!
}