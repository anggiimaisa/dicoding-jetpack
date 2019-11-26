package com.example.themovieapp.ui.MovieFavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.ui.movie.MovieViewModel
import com.example.themovieapp.utils.FakeDataDummy
import com.example.themovieapp.utils.PagedListUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MovieFavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var movieFavoriteViewModel: MovieFavoriteViewModel? = null

    @Mock
    lateinit var repository: MovieFavoriteRepository

    @Mock
    lateinit var observerMovie: Observer<PagedList<Movie>>

//    @Mock
//    lateinit var observerTVShow: Observer<List<TVShow>>

    @Mock
    lateinit var pagedMovieList: PagedList<Movie>
    lateinit var mPagedMovieList : PagedList<Movie>
    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        movieFavoriteViewModel = MovieFavoriteViewModel(repository)
        mPagedMovieList = PagedListUtil.mockPagedList(FakeDataDummy.getMovieDatas())
    }

    @Test
    fun getMovies() {
        val currentMovies = MutableLiveData<PagedList<Movie>>()
        currentMovies.value = mPagedMovieList

        `when`(repository.allMoviesFavorite()).thenReturn(currentMovies)
        movieFavoriteViewModel?.getMoviesFavorite?.observeForever(observerMovie)
        verify<Observer<PagedList<Movie>>>(observerMovie).onChanged(PagedListUtil.mockPagedList(FakeDataDummy.getMovieDatas()))
    }

//    @Test
//    fun getTVShow() {
//        val dummyTVShows = FakeDataDummy.getTVShowDatas()
//        val currentMovies = MutableLiveData<List<TVShow>>()
//        currentMovies.setValue(dummyTVShows)
//
//        Mockito.`when`(repository.getTVShows()).thenReturn(currentMovies)
//        movieViewModel!!.getCurrentTVShows().observeForever(observerTVShow)
//        Mockito.verify<Observer<List<TVShow>>>(observerTVShow).onChanged(dummyTVShows)
//    }

}