package com.omid.filimo.fragments.bookmarkFragment

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
import com.omid.filimo.model.VideoBookmark
import com.omid.filimo.utils.configuration.AppConfiguration

class BookmarkAdapter(private val bookmark: MutableList<VideoBookmark>, private val fragment: Fragment) : RecyclerView.Adapter<BookmarkAdapter.BookmarkVH>() {

    inner class BookmarkVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvBookmark = itemView.findViewById<CardView>(R.id.cv_bookmark)!!
        val imgBookmark = itemView.findViewById<AppCompatImageView>(R.id.img_bookmark)!!
        val txtBookmark = itemView.findViewById<AppCompatTextView>(R.id.txt_bookmark)!!
    }

    private val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkVH {
        return BookmarkVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.bookmark_row, null))
    }

    override fun getItemCount(): Int {
        return bookmark.size
    }

    override fun onBindViewHolder(holder: BookmarkVH, position: Int) {
        holder.apply {
            val bookmarkInfo = bookmark[position]
            Glide.with(AppConfiguration.getContext()).load(bookmarkInfo.videoThumbnailB).into(imgBookmark)
            txtBookmark.text = bookmarkInfo.videoTitle
            cvBookmark.setOnClickListener {
                val video = Video(
                    bookmarkInfo.catId,
                    bookmarkInfo.categoryImage,
                    bookmarkInfo.categoryImageThumb,
                    bookmarkInfo.categoryName,
                    bookmarkInfo.cid,
                    bookmarkInfo.id,
                    bookmarkInfo.rateAvg,
                    bookmarkInfo.totalViewer,
                    bookmarkInfo.videoDescription,
                    bookmarkInfo.videoDuration,
                    bookmarkInfo.videoId,
                    bookmarkInfo.videoThumbnailB,
                    bookmarkInfo.videoThumbnailS,
                    bookmarkInfo.videoTitle,
                    bookmarkInfo.videoType,
                    bookmarkInfo.videoUrl
                )
                bundle.putParcelable("video", video)
                fragment.findNavController().navigate(R.id.action_myFilmsFragment_to_videoPlayerFragment, bundle)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
        }
    }
}