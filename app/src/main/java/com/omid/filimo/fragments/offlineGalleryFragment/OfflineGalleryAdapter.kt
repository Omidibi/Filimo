package com.omid.filimo.fragments.offlineGalleryFragment

import android.media.MediaMetadataRetriever
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
import java.io.File

class OfflineGalleryAdapter(private val videoOffline: List<File>, private val fragment: Fragment) : RecyclerView.Adapter<OfflineGalleryAdapter.OfflineGalleryVH>() {

    inner class OfflineGalleryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvOffline = itemView.findViewById<CardView>(R.id.cv_offline)!!
        val imgOffline = itemView.findViewById<AppCompatImageView>(R.id.img_offline)!!
        val txtOffline = itemView.findViewById<AppCompatTextView>(R.id.txt_offline)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineGalleryVH {
        return OfflineGalleryVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.offline_gallery_row, null))
    }

    override fun getItemCount(): Int {
        return videoOffline.size
    }

    override fun onBindViewHolder(holder: OfflineGalleryVH, position: Int) {
        holder.apply {
            val videoOffline = videoOffline[position]
            if (videoOffline.exists()) {
                txtOffline.text = videoOffline.name
                val retriever = MediaMetadataRetriever()
                retriever.setDataSource(videoOffline.path)
                val bitmap = retriever.frameAtTime
                Glide.with(AppConfiguration.getContext()).load(bitmap).into(imgOffline)
                cvOffline.setOnClickListener {
                    bundle.putString("play_offline", videoOffline.toURI().toString())
                    fragment.findNavController().navigate(R.id.action_myFilmsFragment_to_videoPlayerOfflineFragment, bundle)
                    MainWidget.bnv.visibility = View.GONE
                    MainWidget.toolbar.visibility = View.GONE
                }
            }
        }
    }
}