package com.omid.filimo.fragments.videoPlayerFragment

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.omid.filimo.R

class ShowCommentVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val userName = itemView.findViewById<AppCompatTextView>(R.id.userName)!!
    val showComment = itemView.findViewById<AppCompatTextView>(R.id.show_comment)!!
}