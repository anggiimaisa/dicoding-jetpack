package com.example.themovieapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow
import com.example.themovieapp.databinding.LayoutMovieItemBinding

class TVShowAdapter(@Nullable onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {

    var currentData : List<TVShow> = ArrayList()
    var onItemClickListener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(movie: TVShow)
    }

    init {
        if (onItemClickListener != null) {
            this.onItemClickListener = onItemClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentData[position])
    }

    fun setListDatas(data: List<TVShow>) {
        currentData = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: LayoutMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val mBinding : LayoutMovieItemBinding = binding

        fun bind(tvShow: TVShow) {
            val movie = Movie(tvShow.movieId, tvShow.movieTitle, tvShow.movieDescription, tvShow.movieImage)
            mBinding.movie = movie
            Glide.with(mBinding.root.context)
                .load(movie.movieImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(mBinding.movieImage)
            mBinding.cardMovie.setOnClickListener({
                if (onItemClickListener != null)
                    onItemClickListener?.onClick(tvShow)
            })
        }
    }

}