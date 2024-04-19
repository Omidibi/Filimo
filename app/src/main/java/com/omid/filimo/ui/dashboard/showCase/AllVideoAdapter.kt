package com.omid.filimo.ui.dashboard.showCase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class AllVideoAdapter(private val list: List<Video>) : RecyclerView.Adapter<AllVideoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllVideoVH {
        return AllVideoVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.all_video_row, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllVideoVH, position: Int) {
        holder.apply {
            val allVideo = list[position]
            Glide.with(AppConfiguration.getContext()).load(allVideo.videoThumbnailB).into(imgVideo)
            txtTitle.text = allVideo.videoTitle
        }
    }
}