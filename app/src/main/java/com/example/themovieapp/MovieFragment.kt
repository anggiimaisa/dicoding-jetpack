package com.example.themovieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.databinding.FragmentMoviesBinding
import com.example.themovieapp.ui.MovieFavorite.MovieFavoriteViewModel
import com.example.themovieapp.ui.movie.*
import com.example.themovieapp.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    lateinit var binding : FragmentMoviesBinding
    lateinit var movieViewModel: MovieViewModel
    lateinit var movieFavoriteViewModel: MovieFavoriteViewModel
    lateinit var movieAdapter: MovieAdapter
    lateinit var moviePagedListAdapter: MoviePagedListAdapter
    lateinit var tvShowPagedListAdapter: TVShowPagedListAdapter
    lateinit var tvShowAdapter: TVShowAdapter
    private val MOVIE = 0
    private val TV_SHOW = 1
    private val LOCAL = 0
    private val DB = 1
    var viewType : Int? = null
    var pos : Int? = null

    companion object {
        val ARG_POS = "position"
        val ARG_VIEW_TYPE = "viewType"

        fun getInstance(pos: Int, viewType: Int) : Fragment {
            val fragment = MovieFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POS, pos)
            bundle.putInt(ARG_VIEW_TYPE, viewType)
            fragment.arguments = bundle
            return fragment
        }

        @NonNull
        private fun obtainMovieViewModel(fragment: MovieFragment) : MovieViewModel {
            val viewModelFactory = ViewModelFactory.getInstance(fragment.activity?.application!!)
            return ViewModelProviders.of(fragment, viewModelFactory).get(MovieViewModel::class.java)
        }

        @NonNull
        private fun obtainMovieFavoriteViewModel(fragment: MovieFragment) : MovieFavoriteViewModel {
            val viewModelFactory = ViewModelFactory.getInstance(fragment.activity?.application!!)
            return ViewModelProviders.of(fragment, viewModelFactory).get(MovieFavoriteViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pos = arguments?.getInt(ARG_POS)
        viewType = arguments?.getInt(ARG_VIEW_TYPE)

        movieViewModel = obtainMovieViewModel(this)
        movieFavoriteViewModel = obtainMovieFavoriteViewModel(this)
        movieAdapter = MovieAdapter(object : MovieAdapter.OnItemClickListener{
            override fun onClick(movie: Movie) {
                startActivity(DetailMovieActivity.getIntent(context!!, movie.movieId, pos!!))
            }
        })
        moviePagedListAdapter = MoviePagedListAdapter(object : MoviePagedListAdapter.OnItemClickListener{
            override fun onClick(movie: Movie) {
                startActivity(DetailMovieActivity.getIntent(context!!, movie.movieId, pos!!))
            }
        })
        tvShowPagedListAdapter = TVShowPagedListAdapter(object : TVShowPagedListAdapter.OnItemClickListener{
            override fun onClick(tvShow: TVShow) {
                startActivity(DetailMovieActivity.getIntent(context!!, tvShow.movieId, pos!!))
            }
        })
        tvShowAdapter = TVShowAdapter(object : TVShowAdapter.OnItemClickListener{
            override fun onClick(movie: TVShow) {
                startActivity(DetailMovieActivity.getIntent(context!!, movie.movieId, pos!!))
            }
        })

        when(viewType) {
            LOCAL -> {
                when(pos) {
                    MOVIE -> {
                        movieViewModel.getCurrentMovies().observe(this, Observer {
                            movieAdapter.setListDatas(it)
                        })
                    }
                    TV_SHOW -> {
                        movieViewModel.getCurrentTVShows().observe(this, Observer {
                            tvShowAdapter.setListDatas(it)
                        })
                    }
                }
            }
            DB -> {
                when(pos) {
                    MOVIE -> {
                        movieFavoriteViewModel.getMoviesFavorite.observe(this, Observer {
                            moviePagedListAdapter.submitList(it)
                        })
                    }
                    TV_SHOW -> {
                        movieFavoriteViewModel.getTVShowFavorite.observe(this, Observer {
                            tvShowPagedListAdapter.submitList(it)
                        })
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (viewType!!) {
            LOCAL -> {
                when (pos!!) {
                    MOVIE -> {
                        binding.moviesList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = movieAdapter
                        }
                    }
                    TV_SHOW -> {
                        binding.moviesList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = tvShowAdapter
                        }
                    }
                }
            }
            DB -> {
                when (pos!!) {
                    MOVIE -> {
                        binding.moviesList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = moviePagedListAdapter
                        }
                    }
                    TV_SHOW -> {
                        binding.moviesList.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = tvShowPagedListAdapter
                        }
                    }
                }
            }
        }

    }
}