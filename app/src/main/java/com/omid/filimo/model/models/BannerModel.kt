package com.omid.filimo.model.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerModel(
    @SerializedName("ALL_IN_ONE_VIDEO")
    val banner: List<Banner>
) : Parcelable