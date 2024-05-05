package com.omid.filimo.fragments.videoListByCatIdFragment

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
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class VideoListByCatIdAdapter(private val list: List<Video>, private val fragment: Fragment) : RecyclerView.Adapter<VideoListByCatIdAdapter.VideoListByCatIdVH>() {

    inner class VideoListByCatIdVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCatById = itemView.findViewById<CardView>(R.id.cv_cat_by_id)!!
        val imgCatById = itemView.findViewById<AppCompatImageView>(R.id.img_cat_by_id)!!
        val txtCatById = itemView.findViewById<AppCompatTextView>(R.id.txt_cat_by_id)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListByCatIdVH {
        return VideoListByCatIdVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.cat_by_id_row, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideoListByCatIdVH, position: Int) {
        holder.apply {
            val catByIdInfo = list[position]
            txtCatById.text = catByIdInfo.videoTitle
            Glide.with(AppConfiguration.getContext()).load(catByIdInfo.videoThumbnailB).into(imgCatById)
            cvCatById.setOnClickListener {
                bundle.putParcelable("video", catByIdInfo)
                fragment.findNavController().navigate(R.id.action_videoListByCatIdFragment_to_videoPlayerFragment, bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}