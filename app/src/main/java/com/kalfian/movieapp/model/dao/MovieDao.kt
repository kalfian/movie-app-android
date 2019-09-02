package com.kalfian.movieapp.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kalfian.movieapp.model.ResponseMovie

@Dao
interface MovieDao{
    @Insert
    fun insertMovie(data : ResponseMovie.ResultMovie)

    @Query("SELECT * FROM movie_db")
    fun getAllMovie() : List<ResponseMovie.ResultMovie>

    @Query("SELECT * FROM movie_db WHERE id == :id")
    fun getSelectMovie(id : Long) : ResponseMovie.ResultMovie

    @Query("DELETE FROM movie_db WHERE id == :id")
    fun removeSpecific(id : Long)
}