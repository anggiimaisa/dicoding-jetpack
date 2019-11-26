package com.example.themovieapp.data.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import com.example.themovieapp.utils.LiveDataTestUtil
import com.example.themovieapp.utils.FakeDataDummy
import org.junit.Rule
import org.junit.Test


class DetailMovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val fakeRepository = FakeDetailMovieRepository()
    private val fakeMovie = FakeDataDummy.getMovieDatas().get(1)
    private val fakeTVShow = FakeDataDummy.getTVShowDatas().get(1)

    @Test
    fun getDetailMovie() {
        val movie = LiveDataTestUtil.getValue(fakeRepository.getMovieById(2)!!)

        assertNotNull(movie)
        assertEquals(movie.movieId, fakeMovie.movieId)
        assertEquals(movie.movieTitle, fakeMovie.movieTitle)
        assertEquals(movie.movieDescription, fakeMovie.movieDescription)
        assertEquals(movie.movieImage, fakeMovie.movieImage)
    }


    @Test
    fun getDetailTVShow() {
        val tvShow = LiveDataTestUtil.getValue(fakeRepository.getTvShowById(2)!!)

        assertNotNull(tvShow)
        assertEquals(tvShow.movieId, fakeTVShow.movieId)
        assertEquals(tvShow.movieTitle, fakeTVShow.movieTitle)
        assertEquals(tvShow.movieDescription, fakeTVShow.movieDescription)
        assertEquals(tvShow.movieImage, fakeTVShow.movieImage)
    }

}