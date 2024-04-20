package com.omid.filimo.fragments.searchFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.Search
import com.omid.filimo.utils.configuration.AppConfiguration

class SearchAdapter(private val list: List<Search>): RecyclerView.Adapter<SearchVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
       return SearchVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.search_row,null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        holder.apply {
            val searchInfo = list[position]
            txtSearch.text = searchInfo.videoTitle
            Glide.with(AppConfiguration.getContext()).load(searchInfo.videoThumbnailB).into(imgSearch)
        }
    }
}