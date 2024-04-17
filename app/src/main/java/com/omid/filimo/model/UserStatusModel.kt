package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class UserStatusModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val userStatus: List<UserStatus>
)