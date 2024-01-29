package com.omid.filimo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.models.Video
import com.omid.filimo.util.configuration.AppConfiguration

class VideoListByCatIdAdapter(private val list: List<Video>) : RecyclerView.Adapter<VideoListByCatIdVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListByCatIdVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.cat_by_id_video_row, null)
        return VideoListByCatIdVH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideoListByCatIdVH, position: Int) {
        val categoryVideoList = list[position]
        holder.txtCatById.text = categoryVideoList.videoTitle
        Glide.with(AppConfiguration.getContext()).load(categoryVideoList.videoThumbnailS).into(holder.imgCatById)

        holder.cardCatById.setOnClickListener {

        }
    }
}