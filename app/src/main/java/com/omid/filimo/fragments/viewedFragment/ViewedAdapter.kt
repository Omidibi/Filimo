package com.omid.filimo.fragments.viewedFragment

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

class ViewedAdapter(private val video: MutableList<Video>,private val fragment: Fragment): RecyclerView.Adapter<ViewedVH>() {

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedVH {
        return ViewedVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.viewed_row,null))
    }

    override fun getItemCount(): Int {
        return video.size
    }

    override fun onBindViewHolder(holder: ViewedVH, position: Int) {
        holder.apply {
            val videoInfo = video[position]
            Glide.with(AppConfiguration.getContext()).load(videoInfo.videoThumbnailB).into(imgViewed)
            txtViewed.text = videoInfo.videoTitle
            cvViewed.setOnClickListener {
                bundle.putParcelable("video",videoInfo)
                fragment.findNavController().navigate(R.id.action_myFilmsFragment_to_videoPlayerFragment,bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}