package com.omid.filimo.fragments.offlineGalleryFragment

import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.utils.configuration.AppConfiguration

class OfflineGalleryAdapterQ(private val videoOffline: List<Uri>, private val fragment: Fragment) : RecyclerView.Adapter<OfflineGalleryAdapterQ.OfflineGalleryQVH>() {

    inner class OfflineGalleryQVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvOfflineQ = itemView.findViewById<CardView>(R.id.cv_offline_q)!!
        val imgOfflineQ = itemView.findViewById<AppCompatImageView>(R.id.img_offline_q)!!
        val txtOfflineQ = itemView.findViewById<AppCompatTextView>(R.id.txt_offline_q)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineGalleryQVH {
        return OfflineGalleryQVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.offline_gallery_q_row, null))
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
                bundle.putString("play_offline", videoUri.toString())
                fragment.findNavController().navigate(R.id.action_myFilmsFragment_to_videoPlayerOfflineFragment, bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}