package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class UserProfileModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val userProfile: List<UserProfile>
)