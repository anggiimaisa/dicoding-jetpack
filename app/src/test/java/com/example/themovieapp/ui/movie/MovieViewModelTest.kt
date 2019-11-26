package com.example.themovieapp.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.db.MovieRoomDatabase
import com.example.themovieapp.utils.FakeDataDummy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var movieViewModel: MovieViewModel? = null

    @Mock
    lateinit var repository: MovieRepository

    @Mock
    lateinit var observerMovie: Observer<List<Movie>>

    @Mock
    lateinit var observerTVShow: Observer<List<TVShow>>

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        movieViewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = FakeDataDummy.getMovieDatas()
        val currentMovies = MutableLiveData<List<Movie>>()
        currentMovies.setValue(dummyMovies)

        `when`(repository.getMovies()).thenReturn(currentMovies)
        movieViewModel!!.getCurrentMovies().observeForever(observerMovie)
        verify<Observer<List<Movie>>>(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getTVShow() {
        val dummyTVShows = FakeDataDummy.getTVShowDatas()
        val currentMovies = MutableLiveData<List<TVShow>>()
        currentMovies.setValue(dummyTVShows)

        `when`(repository.getTVShows()).thenReturn(currentMovies)
        movieViewModel!!.getCurrentTVShows().observeForever(observerTVShow)
        verify<Observer<List<TVShow>>>(observerTVShow).onChanged(dummyTVShows)
    }
}