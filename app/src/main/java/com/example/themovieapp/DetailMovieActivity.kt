package com.example.themovieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.databinding.ActivityDetailMovieBinding
import com.example.themovieapp.ui.MovieFavorite.MovieFavoriteViewModel
import com.example.themovieapp.ui.detail.DetailMovieViewModel
import com.example.themovieapp.ui.movie.MovieViewModel
import com.example.themovieapp.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailMovieBinding
    lateinit var viewModel: DetailMovieViewModel
    lateinit var movieViewModel: MovieViewModel
    lateinit var movieFavoriteViewModel: MovieFavoriteViewModel
    lateinit var movie: Movie
    lateinit var tvShow: TVShow
    private var isFavorite = false
    private var menuItem: Menu? = null
    private val MOVIE = 0
    private val TV_SHOW = 1
    var viewType: Int? = null

    companion object {
        const val EXTRA_ID = "movieId"
        const val EXTRA_VIEW_TYPE = "viewType"

        fun getIntent(context: Context, movieId: Int, viewType: Int) : Intent {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(EXTRA_ID, movieId)
            intent.putExtra(EXTRA_VIEW_TYPE, viewType)
            return intent
        }

        private fun obtainDetailMovieViewModel(activity: DetailMovieActivity) : DetailMovieViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel::class.java)
        }

        @NonNull
        private fun obtainMovieViewModel(activity: DetailMovieActivity) : MovieViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
        }

        @NonNull
        private fun obtainMovieFavoriteViewModel(activity : DetailMovieActivity) : MovieFavoriteViewModel {
            val viewModelFactory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, viewModelFactory).get(MovieFavoriteViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent?.getIntExtra(EXTRA_ID, 0)
        viewType = intent?.getIntExtra(EXTRA_VIEW_TYPE, 0)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        viewModel = obtainDetailMovieViewModel(this)
        movieViewModel = obtainMovieViewModel(this)
        movieFavoriteViewModel = obtainMovieFavoriteViewModel(this)

        viewModel.setId(movieId!!)
        getData()
    }

    private fun favoriteState() {
        when (viewType) {
            MOVIE -> {
                movieFavoriteViewModel.getMovieFavoriteById(movie.movieId).observe(this, Observer {
                    if (it != null)
                        isFavorite = true
                })
            }
            TV_SHOW -> {
                movieFavoriteViewModel.getTVShowFavoriteById(tvShow.movieId).observe(this, Observer {
                    if (it != null)
                        isFavorite = true
                })
            }
        }
    }

    private fun getData() {
        when (viewType) {
            MOVIE -> {
                getDetailMovie()
            }
            TV_SHOW -> {
                getDetailTVShow()
            }
        }
    }

    private fun getDetailMovie(){
        viewModel.getCurrentDetailMovie().observe(this, Observer {
            movie = it
            binding.movieTitle.text = it.movieTitle
            binding.movieDescription.text = it.movieDescription
            Glide.with(this)
                .load(it.movieImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.movieImage)

            favoriteState()
            setFavorite()
        })
    }

    private fun getDetailTVShow(){
        viewModel.getCurrentDetailTVShow().observe(this, Observer {
            tvShow = it
            binding.movieTitle.text = it.movieTitle
            binding.movieDescription.text = it.movieDescription
            Glide.with(this)
                .load(it.movieImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.movieImage)

            favoriteState()
            setFavorite()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu_fav, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.fav_bar -> {

                if (!isFavorite)
                    addToFavorite()
                else
                    removeFromFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return true
    }
    private fun removeFromFavorite() {
        when (viewType) {
            MOVIE -> {
                movieFavoriteViewModel.deleteMovieFavorite(movie.movieId)
            }
            TV_SHOW -> {
                movieFavoriteViewModel.deleteTVShowFavorite(tvShow.movieId)
            }
        }
    }

    private fun addToFavorite() {
        when (viewType) {
            MOVIE -> {
                movieFavoriteViewModel.insertMovieFavorite(Movie(movie.movieId, movie.movieTitle, movie.movieDescription, movie.movieImage))
            }
            TV_SHOW -> {
                movieFavoriteViewModel.insertTVShowFavorite(TVShow(tvShow.movieId, tvShow.movieTitle, tvShow.movieDescription, tvShow.movieImage))
            }
        }
    }

    private fun setFavorite() {
        when (isFavorite) {
            true -> menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            false -> menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        }
    }

}