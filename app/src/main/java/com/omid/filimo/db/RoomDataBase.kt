package com.omid.filimo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omid.filimo.model.Video
import com.omid.filimo.model.VideoBookmark

@Database(entities = [Video::class, VideoBookmark::class], version = 1)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun dao(): IDao
}