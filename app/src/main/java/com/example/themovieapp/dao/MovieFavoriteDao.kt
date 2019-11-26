package com.example.themovieapp.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.themovieapp.data.Movie

@Dao
interface MovieFavoriteDao {
    @Query("SELECT * FROM Movie")
    fun getAllMoviesFavorite() : DataSource.Factory<Int, Movie>
    @Query("SELECT * FROM Movie WHERE movieId = (:id)")
    fun getMovieById(id: Int) : LiveData<Movie>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMovies(movieFavorite: Movie)
    @Query("DELETE FROM Movie WHERE movieId = (:id)")
    fun deleteMovieFavoriteById(id: Int)
    @Query("DELETE FROM Movie")
    fun deleteAllMovies()
}