package com.omid.filimo.model

import com.google.gson.annotations.SerializedName

data class ForgetPasswordModel(

    @SerializedName("ALL_IN_ONE_VIDEO")
    val forgetPassword: List<ForgetPassword>
)