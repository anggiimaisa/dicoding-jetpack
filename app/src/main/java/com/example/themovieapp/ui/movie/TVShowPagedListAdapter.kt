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
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.databinding.LayoutMovieItemBinding


class TVShowPagedListAdapter(@Nullable onItemClickListener: OnItemClickListener) : PagedListAdapter<TVShow, TVShowPagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener : OnItemClickListener? = null

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShow>(){
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.movieTitle.equals(newItem.movieTitle)
            }

            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.equals(newItem)
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(tvShow: TVShow)
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

        fun bind(tvShow: TVShow){
            val movie = Movie(tvShow.movieId, tvShow.movieTitle, tvShow.movieDescription, tvShow.movieImage)
            mBinding.movie = movie
            Glide.with(mBinding.root.context)
                .load(tvShow.movieImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(mBinding.movieImage)
            mBinding.cardMovie.setOnClickListener({
                if (onItemClickListener != null)
                    onItemClickListener?.onClick(tvShow)
            })
        }

    }

}

