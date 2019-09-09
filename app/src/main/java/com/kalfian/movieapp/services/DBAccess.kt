package com.kalfian.movieapp.services

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kalfian.movieapp.model.dao.MovieDao
import com.kalfian.movieapp.model.dao.TvshowDao
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow

@Database(entities = [(ResponseMovie.ResultMovie::class),(ResponseTvShow.ResultTvShow::class)], version = 5, exportSchema = false)
abstract class DBAccess : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    abstract fun tvshowDao() : TvshowDao

    companion object {
        private var instance: DBAccess? = null

        @Synchronized
        fun getInstance(context: Context): DBAccess {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, DBAccess::class.java, "favorite")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}