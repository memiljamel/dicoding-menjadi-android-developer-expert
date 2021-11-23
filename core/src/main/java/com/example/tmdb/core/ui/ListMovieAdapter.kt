package com.example.tmdb.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdb.core.R
import com.example.tmdb.core.databinding.MovieItemListBinding
import com.example.tmdb.core.domain.model.Movie
import java.util.*

class ListMovieAdapter : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    private var movieList = ArrayList<Movie>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(data: List<Movie>?) {
        if (data == null) return
        movieList.clear()
        movieList.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            MovieItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = movieList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(movieList[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ListViewHolder(private val binding: MovieItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(itemView.resources.getString(R.string.poster, movie.poster))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_custom_loading))
                .error(R.drawable.ic_custom_error)
                .into(binding.ivPoster)
            binding.tvTitle.text = movie.title
            binding.tvOverview.text = movie.overview
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }
}