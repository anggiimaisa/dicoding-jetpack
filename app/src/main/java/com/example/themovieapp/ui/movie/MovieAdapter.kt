package com.example.themovieapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovieapp.R
import com.example.themovieapp.data.Movie
import com.example.themovieapp.databinding.LayoutMovieItemBinding

class MovieAdapter(@Nullable onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var currentDatas : List<Movie> = ArrayList()
    var onItemClickListener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(movie: Movie)
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
        return currentDatas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentDatas[position])
    }

    fun setListDatas(datas: List<Movie>) {
        currentDatas = datas
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: LayoutMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val mBinding : LayoutMovieItemBinding = binding

        fun bind(movie: Movie) {
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