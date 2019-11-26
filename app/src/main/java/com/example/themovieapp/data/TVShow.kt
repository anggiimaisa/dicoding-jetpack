package com.example.themovieapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TVShow(
    @PrimaryKey
    val movieId: Int,
    @ColumnInfo(name = "movie_title")
    val movieTitle: String,
    @ColumnInfo(name = "movie_description")
    val movieDescription: String,
    @ColumnInfo(name = "movie_image")
    val movieImage: Int
)