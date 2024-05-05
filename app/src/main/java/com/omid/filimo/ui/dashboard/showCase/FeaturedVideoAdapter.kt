package com.omid.filimo.ui.dashboard.showCase

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

class FeaturedVideoAdapter(private val featuredList: List<Video>, private val fragment: Fragment) : RecyclerView.Adapter<FeaturedVideoAdapter.FeaturedVH>() {

    inner class FeaturedVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvFeatured = itemView.findViewById<CardView>(R.id.cv_featured)!!
        val imgFeatured = itemView.findViewById<AppCompatImageView>(R.id.img_featured)!!
        val txtFeatured = itemView.findViewById<AppCompatTextView>(R.id.txt_featured)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedVH {
        return FeaturedVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.featured_row, null))
    }

    override fun getItemCount(): Int {
        return featuredList.size
    }

    override fun onBindViewHolder(holder: FeaturedVH, position: Int) {
        holder.apply {
            val featuredList = featuredList[position]
            Glide.with(AppConfiguration.getContext()).load(featuredList.videoThumbnailB).into(imgFeatured)
            txtFeatured.text = featuredList.videoTitle
            cvFeatured.setOnClickListener {
                bundle.putParcelable("video", featuredList)
                fragment.findNavController().navigate(R.id.action_showCaseFragment_to_videoPlayerFragment, bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}