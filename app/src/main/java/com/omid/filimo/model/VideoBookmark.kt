package com.omid.filimo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity("tbl_bookmark")
@Parcelize
data class VideoBookmark(
    @ColumnInfo("cat_id")
    @SerializedName("cat_id")
    val catId: String,
    @ColumnInfo("category_image")
    @SerializedName("category_image")
    val categoryImage: String,
    @ColumnInfo("category_image_thumb")
    @SerializedName("category_image_thumb")
    val categoryImageThumb: String,
    @ColumnInfo("category_name")
    @SerializedName("category_name")
    val categoryName: String,
    @ColumnInfo("cid")
    @SerializedName("cid")
    val cid: String,
    @ColumnInfo("id")
    @SerializedName("id")
    val id: String,
    @ColumnInfo("rate_avg")
    @SerializedName("rate_avg")
    val rateAvg: String,
    @ColumnInfo("total_viewer")
    @SerializedName("totel_viewer")
    val totalViewer: String,
    @ColumnInfo("video_description")
    @SerializedName("video_description")
    val videoDescription: String,
    @ColumnInfo("video_duration")
    @SerializedName("video_duration")
    val videoDuration: String,
    @ColumnInfo("video_id")
    @SerializedName("video_id")
    val videoId: String,
    @ColumnInfo("video_thumbnail_b")
    @SerializedName("video_thumbnail_b")
    val videoThumbnailB: String,
    @ColumnInfo("video_thumbnail_s")
    @SerializedName("video_thumbnail_s")
    val videoThumbnailS: String,
    @ColumnInfo("video_title")
    @SerializedName("video_title")
    val videoTitle: String,
    @ColumnInfo("video_type")
    @SerializedName("video_type")
    val videoType: String,
    @ColumnInfo("video_url")
    @SerializedName("video_url")
    val videoUrl: String,
    @PrimaryKey(autoGenerate = true)
    val idPrimaryKey: Int = 0
): Parcelable