package com.example.themovieapp.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themovieapp.data.TVShow

@Dao
interface TVShowFavoriteDao {
    @Query("SELECT * FROM TVShow")
    fun getAllTVShowsFavorite() : DataSource.Factory<Int, TVShow>
    @Query("SELECT * FROM TVShow WHERE movieId = (:id)")
    fun getTVShowById(id: Int) : LiveData<TVShow>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllTVShows(tvShow: TVShow)
    @Query("DELETE FROM TVShow WHERE movieId = (:id)")
    fun deleteTVShowFavoriteById(id: Int)
    @Query("DELETE FROM TVShow")
    fun deleteAllTVShows()
}