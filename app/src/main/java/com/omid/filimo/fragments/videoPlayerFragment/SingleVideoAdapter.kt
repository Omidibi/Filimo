package com.omid.filimo.fragments.videoPlayerFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.Related
import com.omid.filimo.model.Video
import com.omid.filimo.utils.configuration.AppConfiguration

class SingleVideoAdapter(private val list: List<Related>, private val iSelected: IOnSelectListener) : RecyclerView.Adapter<SingleVideoAdapter.SingleVideoVH>() {

    inner class SingleVideoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvSingleVideo = itemView.findViewById<CardView>(R.id.cv_single_video)!!
        val imgSingleVideo = itemView.findViewById<AppCompatImageView>(R.id.img_single_video)!!
        val txtSingleVideo = itemView.findViewById<AppCompatTextView>(R.id.txt_single_video)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleVideoVH {
        return SingleVideoVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.single_video_row, null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SingleVideoVH, position: Int) {
        holder.apply {
            val relatedInfo = list[position]
            txtSingleVideo.text = relatedInfo.videoTitle
            Glide.with(AppConfiguration.getContext()).load(relatedInfo.videoThumbnailB).into(imgSingleVideo)
            cvSingleVideo.setOnClickListener {
                val videoList = Video(
                    relatedInfo.catId,
                    "",
                    "",
                    relatedInfo.categoryName,
                    "",
                    relatedInfo.id,
                    relatedInfo.rateAvg,
                    relatedInfo.totalViewer,
                    relatedInfo.videoDescription,
                    relatedInfo.videoDuration,
                    relatedInfo.videoId,
                    relatedInfo.videoThumbnailB,
                    relatedInfo.videoThumbnailS,
                    relatedInfo.videoTitle,
                    relatedInfo.videoType,
                    relatedInfo.videoUrl
                )
                iSelected.onSelect(videoList)
            }
        }
    }
}