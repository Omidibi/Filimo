package com.omid.filimo.fragments.latestVideoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class LatestVideoListAdapter(private val list: List<Video>,private val fragment: Fragment): RecyclerView.Adapter<LatestVideoListVH>() {

    private val bundle = Bundle()

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
            cvLatest.setOnClickListener {
                bundle.putParcelable("video",latestListInfo)
                fragment.findNavController().navigate(R.id.action_latestVideoFragment_to_videoPlayerFragment,bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}