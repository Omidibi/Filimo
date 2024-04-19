package com.omid.filimo.fragments.latestVideoFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.LatestVideo
import com.omid.filimo.utils.configuration.AppConfiguration

class LatestVideoListAdapter(private val list: List<LatestVideo>): RecyclerView.Adapter<LatestVideoListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestVideoListVH {
        return LatestVideoListVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.latest_list_row,null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LatestVideoListVH, position: Int) {
        holder.apply {
            val latestListInfo = list[position]
            txtLatest.text = latestListInfo.videoTitle
            Glide.with(AppConfiguration.getContext()).load(latestListInfo.videoThumbnailB).into(imgLatest)
        }
    }
}