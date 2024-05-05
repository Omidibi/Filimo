package com.omid.filimo.fragments.searchFragment

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

class SearchAdapter(private val list: List<Video>, private val fragment: Fragment) : RecyclerView.Adapter<SearchAdapter.SearchVH>() {

    inner class SearchVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvSearch = itemView.findViewById<CardView>(R.id.cv_search)!!
        val imgSearch = itemView.findViewById<AppCompatImageView>(R.id.img_search)!!
        val txtSearch = itemView.findViewById<AppCompatTextView>(R.id.txt_search)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        return SearchVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.search_row, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        holder.apply {
            val searchInfo = list[position]
            txtSearch.text = searchInfo.videoTitle
            Glide.with(AppConfiguration.getContext()).load(searchInfo.videoThumbnailB).into(imgSearch)
            cvSearch.setOnClickListener {
                bundle.putParcelable("video", searchInfo)
                fragment.findNavController().navigate(R.id.action_searchFragment_to_videoPlayerFragment, bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}