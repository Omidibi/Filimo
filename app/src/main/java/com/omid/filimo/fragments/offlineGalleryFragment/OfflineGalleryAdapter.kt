package com.omid.filimo.fragments.offlineGalleryFragment

import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.utils.configuration.AppConfiguration
import java.io.File

class OfflineGalleryAdapter(private val videoOffline: List<File>):RecyclerView.Adapter<OfflineGalleryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineGalleryVH {
        return OfflineGalleryVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.offline_gallery_row,null))
    }

    override fun getItemCount(): Int {
        return videoOffline.size
    }

    override fun onBindViewHolder(holder: OfflineGalleryVH, position: Int) {
        holder.apply {
            val videoOffline = videoOffline[position]
            txtOffline.text = videoOffline.name
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(videoOffline.path)
            val bitmap = retriever.frameAtTime
            Glide.with(AppConfiguration.getContext()).load(bitmap).into(imgOffline)
            cvOffline.setOnClickListener {

            }
        }
    }
}