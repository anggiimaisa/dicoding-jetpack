package com.example.themovieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.utils.DataDummy

class DetailMovieRepository {

    companion object {
        @Volatile
        var INSTANCE : DetailMovieRepository? = null

        fun getInstance() : DetailMovieRepository {
            if (INSTANCE == null)
                synchronized(DetailMovieRepository::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = DetailMovieRepository()
                }
            return INSTANCE!!
        }
    }

    fun getMovieById(movieId: Int): LiveData<Movie>? {
        val currentMovie = MutableLiveData<Movie>()
        val movies: List<Movie> = DataDummy.getMovieDatas()


        for (i in movies.indices) {
            val dummyMoviesId = movies[i].movieId
            if (movieId == dummyMoviesId) {
                currentMovie.setValue(movies[i])
                return currentMovie
            }
        }

        return null
    }

    fun getTvShowById(movieId: Int): LiveData<TVShow>? {
        val currentTVShow = MutableLiveData<TVShow>()
        val tvShow: List<TVShow> = DataDummy.getTVShowDatas()

        for (i in tvShow.indices) {
            val dummyMoviesId = tvShow[i].movieId
            if (movieId == dummyMoviesId) {
                currentTVShow.setValue(tvShow[i])
                return currentTVShow
            }
        }

        return null
    }

}