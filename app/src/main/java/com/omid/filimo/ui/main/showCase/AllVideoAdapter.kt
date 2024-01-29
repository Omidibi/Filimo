package com.omid.filimo.ui.main.showCase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R
import com.omid.filimo.model.models.Video
import com.omid.filimo.util.configuration.AppConfiguration

class AllVideoAdapter(private val list: List<Video>) : RecyclerView.Adapter<AllVideoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllVideoVH {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.all_video_row, null)
        return AllVideoVH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AllVideoVH, position: Int) {
        val allVideo = list[position]

    }
}