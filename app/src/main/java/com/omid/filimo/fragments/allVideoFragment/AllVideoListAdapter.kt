package com.omid.filimo.fragments.allVideoFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.AllVideo
import com.omid.filimo.utils.configuration.AppConfiguration

class AllVideoListAdapter(private val list: List<AllVideo>): RecyclerView.Adapter<AllVideoListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllVideoListVH {
        return AllVideoListVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.all_video_list_row,null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllVideoListVH, position: Int) {
        holder.apply {
            val allVideoInfo = list[position]
            txtAll.text = allVideoInfo.videoTitle
            Glide.with(AppConfiguration.getContext()).load(allVideoInfo.videoThumbnailB).into(imgAll)
        }
    }
}