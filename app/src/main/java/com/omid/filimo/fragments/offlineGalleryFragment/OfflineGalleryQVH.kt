package com.omid.filimo.fragments.offlineGalleryFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class OfflineGalleryQVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvOfflineQ = itemView.findViewById<CardView>(R.id.cv_offline_q)!!
    val imgOfflineQ = itemView.findViewById<AppCompatImageView>(R.id.img_offline_q)!!
    val txtOfflineQ = itemView.findViewById<AppCompatTextView>(R.id.txt_offline_q)!!
}