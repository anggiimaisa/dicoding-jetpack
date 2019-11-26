package com.example.themovieapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.themovieapp.data.Movie
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.utils.FakeDataDummy
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify

class DetailMovieViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var detailMovieViewModel: DetailMovieViewModel? = null
    private var dummyMovie : Movie? = null
    private var dummyTVShow : TVShow? = null
    private var movieId: Int? = null
    private var tvId: Int? = null

    @Mock
    lateinit var repository: DetailMovieRepository

    @Mock
    lateinit var observer: Observer<Movie>

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        detailMovieViewModel = DetailMovieViewModel(repository)
        dummyMovie = FakeDataDummy.getMovieDatas().get(1)
        movieId = dummyMovie?.movieId
        dummyTVShow = FakeDataDummy.getTVShowDatas().get(1)
        tvId = dummyTVShow?.movieId
    }

    @Test
    fun getMovie() {
        val currentMovie = MutableLiveData<Movie>()
        currentMovie.setValue(dummyMovie)

        `when`<LiveData<Movie>>(repository.getMovieById(movieId!!)).thenReturn(currentMovie)

        detailMovieViewModel?.movieId = movieId!!
        detailMovieViewModel?.getCurrentDetailMovie()?.observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTVShow() {
        val currentTVShow = MutableLiveData<Movie>()
        currentTVShow.setValue(dummyMovie)

        `when`<LiveData<Movie>>(repository.getMovieById(tvId!!)).thenReturn(currentTVShow)

        detailMovieViewModel?.movieId = tvId!!
        detailMovieViewModel?.getCurrentDetailMovie()?.observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}