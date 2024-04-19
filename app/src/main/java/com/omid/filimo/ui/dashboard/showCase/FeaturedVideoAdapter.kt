package com.omid.filimo.ui.dashboard.showCase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class FeaturedVideoAdapter(private val featuredList : List<Video>) : RecyclerView.Adapter<FeaturedVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedVH {
        return FeaturedVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.featured_row,null))
    }

    override fun getItemCount(): Int {
        return featuredList.size
    }

    override fun onBindViewHolder(holder: FeaturedVH, position: Int) {
        holder.apply {
            val featuredList = featuredList[position]
            Glide.with(AppConfiguration.getContext()).load(featuredList.videoThumbnailB).into(imgFeatured)
            txtFeatured.text = featuredList.videoTitle
        }
    }
}