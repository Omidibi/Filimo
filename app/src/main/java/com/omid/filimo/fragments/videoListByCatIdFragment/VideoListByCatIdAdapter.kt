package com.omid.filimo.fragments.videoListByCatIdFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class VideoListByCatIdAdapter(private val list: List<Video>): RecyclerView.Adapter<VideoListByCatIdVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListByCatIdVH {
        return VideoListByCatIdVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.cat_by_id_row,null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideoListByCatIdVH, position: Int) {
        holder.apply {
            val catByIdInfo = list[position]
            txtCatById.text = catByIdInfo.videoTitle
            Glide.with(AppConfiguration.getContext()).load(catByIdInfo.videoThumbnailB).into(imgCatById)
        }
    }
}