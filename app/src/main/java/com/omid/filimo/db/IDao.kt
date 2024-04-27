package com.omid.filimo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.omid.filimo.model.Video
import com.omid.filimo.model.VideoBookmark

@Dao
interface IDao {

    @Insert
    fun insertViewed(vararg video: Video)

    @Insert
    fun insertBookmark(vararg videoBookmark: VideoBookmark)

    @Query("Select * From tbl_viewed")
    fun showAllViewed(): MutableList<Video>

    @Query("Select * From tbl_bookmark")
    fun showAllBookmark(): MutableList<VideoBookmark>

    @Query("Delete From tbl_viewed Where idPrimaryKey Like :idPrimaryKey")
    fun deleteViewed(idPrimaryKey: Int): Int

    @Query("Delete From tbl_bookmark Where id Like :id")
    fun deleteBookmark(id: String): Int

    @Query("Select * From tbl_viewed Where id Like :id")
    fun searchByIdViewed(id: String): MutableList<Video>

    @Query("Select * From tbl_bookmark Where id Like :id")
    fun searchByIdBookmark(id: String): MutableList<VideoBookmark>
}