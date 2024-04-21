package com.omid.filimo.ui.dashboard.showCase

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

class LatestVideoAdapter(private val latestList : List<Video>,private val fragment: Fragment) : RecyclerView.Adapter<LatestVH>() {

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestVH {
        return LatestVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.latest_row,null))
    }

    override fun getItemCount(): Int {
        return latestList.size
    }

    override fun onBindViewHolder(holder: LatestVH, position: Int) {
        holder.apply {
            val latest = latestList[position]
            txtLatest.text = latest.videoTitle
            Glide.with(AppConfiguration.getContext()).load(latest.videoThumbnailB).into(imgLatest)
            cvLatest.setOnClickListener {
                bundle.putParcelable("video",latest)
                fragment.findNavController().navigate(R.id.action_showCaseFragment_to_videoPlayerFragment,bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}