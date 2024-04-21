package com.omid.filimo.fragments.allVideoFragment

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

class AllVideoListAdapter(private val list: List<Video>,private val fragment: Fragment): RecyclerView.Adapter<AllVideoListVH>() {

    private val bundle = Bundle()

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
            cvAll.setOnClickListener {
                bundle.putParcelable("video",allVideoInfo)
                fragment.findNavController().navigate(R.id.action_allVideoFragment_to_videoPlayerFragment,bundle)
                MainWidget.toolbar.visibility = View.GONE
                MainWidget.bnv.visibility = View.GONE
            }
        }
    }
}