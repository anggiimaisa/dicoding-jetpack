package com.example.themovieapp.data.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.db.MovieRoomDatabase
import com.example.themovieapp.ui.movie.MovieRepository
import com.example.themovieapp.utils.DataDummy
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FakeMovieRepository {

    companion object {

        @Volatile
        private var INSTANCE : MovieRepository? = null

        fun getInstance() : MovieRepository {
            if(INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository()
                    }
                }
            }
            return INSTANCE!!
        }

    }

    fun getMovies() : LiveData<List<Movie>> {
        val currentMovies : MutableLiveData<List<Movie>> = MutableLiveData()
        currentMovies.value = DataDummy.getMovieDatas()
        return currentMovies
    }

    fun getTVShows() : LiveData<List<TVShow>> {
        val currentTVShows : MutableLiveData<List<TVShow>> = MutableLiveData()
        currentTVShows.value = DataDummy.getTVShowDatas()
        return currentTVShows
    }

}