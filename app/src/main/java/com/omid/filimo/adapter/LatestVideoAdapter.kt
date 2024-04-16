package com.omid.filimo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class LatestVideoAdapter(private val latestList : List<Video>) : RecyclerView.Adapter<LatestVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.latest_row,null)
        return LatestVH(view)
    }

    override fun getItemCount(): Int {
        return latestList.size
    }

    override fun onBindViewHolder(holder: LatestVH, position: Int) {
        holder.apply {
            val latest = latestList[position]
            txtLatest.text = latest.videoTitle
            Glide.with(AppConfiguration.getContext()).load(latest.videoThumbnailB).into(imgLatest)

        }
    }
}