package com.example.themovieapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themovieapp.data.Movie
import com.example.themovieapp.dao.MovieFavoriteDao
import com.example.themovieapp.dao.TVShowFavoriteDao
import com.example.themovieapp.data.TVShow

@Database(entities = [Movie::class, TVShow::class], version = 2, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieFavoriteDao() : MovieFavoriteDao
    abstract fun tvShowFavoriteDao() : TVShowFavoriteDao

    companion object {

        @Volatile
        private var INSTANCE : MovieRoomDatabase? = null

        fun getDatabase(context: Context) : MovieRoomDatabase {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            MovieRoomDatabase::class.java,
                            "movie_database"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}