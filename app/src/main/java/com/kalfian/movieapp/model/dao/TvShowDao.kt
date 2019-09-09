package com.kalfian.movieapp.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.database.Cursor
import com.kalfian.movieapp.model.ResponseTvShow

@Dao
interface TvshowDao{
    @Insert
    fun insertTvshow(data : ResponseTvShow.ResultTvShow)

    @Query("SELECT * FROM tv_show_db")
    fun getAllTvshow() : List<ResponseTvShow.ResultTvShow>

    @Query("SELECT * FROM tv_show_db WHERE id == :id")
    fun getSelectTvshow(id : Long) : ResponseTvShow.ResultTvShow

    @Query("DELETE FROM tv_show_db WHERE id == :id")
    fun removeSpecific(id : Long)

    @Query("SELECT * FROM tv_show_db")
    fun getAllTvShowCursor() : Cursor
}