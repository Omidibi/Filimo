package com.omid.filimo.config

import android.content.Context
import android.content.SharedPreferences
import com.omid.filimo.model.Related
import com.omid.filimo.utils.configuration.AppConfiguration

class AppSettings {

    private var sharedPreferences: SharedPreferences = AppConfiguration.getContext().getSharedPreferences("setting", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun lock(state: Int) {
        editor.putInt("lock", state)
        editor.commit()
    }

    fun getLock(): Int {
        return sharedPreferences.getInt("lock", 0)
    }

    fun email(email: String) {
        editor.putString("email", email)
        editor.commit()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", "")
    }

    fun password(password: String) {
        editor.putString("password", password)
        editor.commit()
    }

    fun getPassword(): String? {
        return sharedPreferences.getString("password", "")
    }

    fun name(name: String) {
        editor.putString("name", name)
        editor.commit()
    }

    fun getName(): String? {
        return sharedPreferences.getString("name", "")
    }

    fun phone(phone: String) {
        editor.putString("phone", phone)
        editor.commit()
    }

    fun getPhone(): String? {
        return sharedPreferences.getString("phone", "")
    }

    fun userId(userId: String?) {
        editor.putString("userId", userId)
        editor.commit()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("userId", "")
    }

    fun saveRelate(related: Related) {
        editor.putString("catId", related.catId)
        editor.putString("categoryName", related.categoryName)
        editor.putString("id", related.id)
        editor.putString("rateAvg", related.rateAvg)
        editor.putString("totalViewer", related.totalViewer)
        editor.putString("videoDescription", related.videoDescription)
        editor.putString("videoDuration", related.videoDuration)
        editor.putString("videoId", related.videoId)
        editor.putString("videoThumbnailB", related.videoThumbnailB)
        editor.putString("videoThumbnailS", related.videoThumbnailS)
        editor.putString("videoTitle", related.videoTitle)
        editor.putString("videoType", related.videoType)
        editor.putString("videoUrl", related.videoUrl)
        editor.apply()
    }

    fun getRelated(): Related {
        val catId = sharedPreferences.getString("catId", "")
        val categoryName = sharedPreferences.getString("categoryName", "")
        val id = sharedPreferences.getString("id", "")
        val rateAvg = sharedPreferences.getString("rateAvg", "")
        val totalViewer = sharedPreferences.getString("totalViewer", "")
        val videoDescription = sharedPreferences.getString("videoDescription", "")
        val videoDuration = sharedPreferences.getString("videoDuration", "")
        val videoId = sharedPreferences.getString("videoId", "")
        val videoThumbnailB = sharedPreferences.getString("videoThumbnailB", "")
        val videoThumbnailS = sharedPreferences.getString("videoThumbnailS", "")
        val videoTitle = sharedPreferences.getString("videoTitle", "")
        val videoType = sharedPreferences.getString("videoType", "")
        val videoUrl = sharedPreferences.getString("videoUrl", "")
        return Related(
            catId!!,
            categoryName!!,
            id!!,
            rateAvg!!,
            totalViewer!!,
            videoDescription!!,
            videoDuration!!,
            videoId!!,
            videoThumbnailB!!,
            videoThumbnailS!!,
            videoTitle!!,
            videoType!!,
            videoUrl!!
        )
    }

    fun saveStatusFragment(status: Int) {
        editor.putInt("statusFragment", status)
        editor.commit()
    }

    fun getStatusFragment(): Int {
        return sharedPreferences.getInt("statusFragment", 0)
    }
}