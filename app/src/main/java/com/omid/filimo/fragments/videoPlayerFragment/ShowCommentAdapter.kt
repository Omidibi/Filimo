package com.omid.filimo.fragments.videoPlayerFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R
import com.omid.filimo.model.UserComment
import com.omid.filimo.utils.configuration.AppConfiguration

class ShowCommentAdapter(private val list: List<UserComment>): RecyclerView.Adapter<ShowCommentVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCommentVH {
        return ShowCommentVH(LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.show_comment_row,null))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ShowCommentVH, position: Int) {
        holder.apply {
            val commentInfo = list[position]
            userName.text = commentInfo.userName
            showComment.text = commentInfo.commentText
        }
    }
}