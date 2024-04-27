package com.omid.filimo.fragments.bookmarkFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class BookmarkVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvBookmark = itemView.findViewById<CardView>(R.id.cv_bookmark)!!
    val imgBookmark = itemView.findViewById<AppCompatImageView>(R.id.img_bookmark)!!
    val txtBookmark = itemView.findViewById<AppCompatTextView>(R.id.txt_bookmark)!!
}