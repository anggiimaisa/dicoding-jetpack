package com.example.themovieapp.ui.MovieFavorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.db.MovieRoomDatabase
import com.example.themovieapp.ui.movie.MovieRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MovieFavoriteRepository(application: Application)  {

    val application = application

    companion object {

        @Volatile
        private var INSTANCE : MovieFavoriteRepository? = null

        fun getInstance(application: Application) : MovieFavoriteRepository {
            if(INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieFavoriteRepository(application)
                    }
                }
            }
            return INSTANCE!!
        }

    }

    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()
    private val movieFavoriteDao = MovieRoomDatabase.getDatabase(application).movieFavoriteDao()
    private val tvShowFavoriteDao = MovieRoomDatabase.getDatabase(application).tvShowFavoriteDao()

    fun allMoviesFavorite() : LiveData<PagedList<Movie>> {
        return LivePagedListBuilder<Int, Movie>(movieFavoriteDao.getAllMoviesFavorite(), 20).build()
    }

    fun allTVShowsFavorite() : LiveData<PagedList<TVShow>> {
        return LivePagedListBuilder<Int, TVShow>(tvShowFavoriteDao.getAllTVShowsFavorite(), 20).build()
    }

    fun getMovieFavorite(movieId: Int) : LiveData<Movie> {
        return movieFavoriteDao.getMovieById(movieId)
    }

    fun getTVShowFavorite(tvShowId: Int) : LiveData<TVShow> {
        return tvShowFavoriteDao.getTVShowById(tvShowId)
    }

    fun insertMovieFavorite(movieFavorite: Movie) {
        executorService.execute{
            movieFavoriteDao.insertAllMovies(movieFavorite)
        }
    }

    fun insertTVShowFavorite(tvShow: TVShow) {
        executorService.execute{
            tvShowFavoriteDao.insertAllTVShows(tvShow)
        }
    }

    fun deleteMovieFavorite(movieId: Int) {
        executorService.execute{
            movieFavoriteDao.deleteMovieFavoriteById(movieId)
        }
    }

    fun deleteTVShowFavorite(tvId: Int) {
        executorService.execute{
            tvShowFavoriteDao.deleteTVShowFavoriteById(tvId)
        }
    }

}