package com.example.themovieapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.data.Movie
import com.example.themovieapp.databinding.LayoutMovieItemBinding


class MoviePagedListAdapter(@Nullable onItemClickListener: OnItemClickListener) : PagedListAdapter<Movie, MoviePagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener : OnItemClickListener? = null

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieTitle.equals(newItem.movieTitle)
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.equals(newItem)
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(movie: Movie)
    }

    init {
        if (onItemClickListener != null) {
            this.onItemClickListener = onItemClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }


    inner class ViewHolder(layoutMovieItemBinding: LayoutMovieItemBinding) : RecyclerView.ViewHolder(layoutMovieItemBinding.root){

        val mBinding = layoutMovieItemBinding

        fun bind(movie: Movie){
            mBinding.movie = movie
            Glide.with(mBinding.root.context)
                .load(movie.movieImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(mBinding.movieImage)
            mBinding.cardMovie.setOnClickListener({
                if (onItemClickListener != null)
                    onItemClickListener?.onClick(movie)
            })
        }

    }

}

