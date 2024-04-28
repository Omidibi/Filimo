package com.omid.filimo.fragments.offlineGalleryFragment

import android.media.MediaMetadataRetriever
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.utils.configuration.AppConfiguration

class OfflineGalleryAdapterQ(private val videoOffline: List<Uri>):RecyclerView.Adapter<OfflineGalleryQVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineGalleryQVH {
        return OfflineGalleryQVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.offline_gallery_q_row,null))
    }

    override fun getItemCount(): Int {
        return videoOffline.size
    }

    override fun onBindViewHolder(holder: OfflineGalleryQVH, position: Int) {
        holder.apply {
            val videoUri = videoOffline[position]
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(AppConfiguration.getContext(), videoUri)
            val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val bitmap = retriever.frameAtTime
            txtOfflineQ.text = title ?: videoUri.lastPathSegment
            Glide.with(AppConfiguration.getContext()).load(bitmap).into(imgOfflineQ)
            cvOfflineQ.setOnClickListener {

            }
        }
    }
}