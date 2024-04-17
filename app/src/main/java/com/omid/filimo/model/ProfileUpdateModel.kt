package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class ProfileUpdateModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val userProfileUpdate: List<ProfileUpdate>
)