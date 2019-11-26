package com.example.themovieapp.data.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import com.example.themovieapp.utils.LiveDataTestUtil
import com.example.themovieapp.utils.FakeDataDummy
import org.junit.Rule
import org.junit.Test


class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val fakeRepository = FakeMovieRepository()

    private val fakeMovies = FakeDataDummy.getMovieDatas()
    private val fakeTVShow = FakeDataDummy.getTVShowDatas()

    @Test
    fun getAllMovies() {
        val movies = LiveDataTestUtil.getValue(fakeRepository.getMovies())
        assertNotNull(movies)
        assertEquals(fakeMovies.size, movies.size)
    }

    @Test
    fun getAllTVShows() {
        val tvShows = LiveDataTestUtil.getValue(fakeRepository.getTVShows())
        assertNotNull(tvShows)
        assertEquals(fakeTVShow.size, tvShows.size)
    }

}