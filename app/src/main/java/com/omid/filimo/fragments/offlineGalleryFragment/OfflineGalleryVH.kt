package com.omid.filimo.fragments.offlineGalleryFragment

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class OfflineGalleryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cvOffline = itemView.findViewById<CardView>(R.id.cv_offline)!!
    val imgOffline = itemView.findViewById<AppCompatImageView>(R.id.img_offline)!!
    val txtOffline = itemView.findViewById<AppCompatTextView>(R.id.txt_offline)!!
}