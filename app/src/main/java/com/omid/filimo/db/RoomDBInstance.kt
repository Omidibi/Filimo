package com.omid.filimo.db

import androidx.room.Room
import com.omid.filimo.utils.configuration.AppConfiguration

object RoomDBInstance {

    val roomDbInstance = Room.databaseBuilder(AppConfiguration.getContext(), RoomDataBase::class.java, "tbl_viewed")
            .allowMainThreadQueries()
            .build()
}