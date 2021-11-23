package com.example.tmdb.detail

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdb.R
import com.example.tmdb.core.domain.model.Movie
import com.example.tmdb.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()

    private var _activityDetailBinding: ActivityDetailBinding? = null
    private val binding get() = _activityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie
        showDetailMovie(movie)
    }

    private fun showDetailMovie(movie: Movie) {
        Glide.with(this)
            .load(resources.getString(R.string.poster, movie.poster))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_custom_loading))
            .error(R.drawable.ic_custom_error)
            .into(binding?.content?.ivPoster as ImageView)
        binding?.content?.tvVote?.text = movie.vote.toString()
        binding?.content?.tvTitle?.text = movie.title
        binding?.content?.tvRelease?.text = movie.release
        binding?.content?.tvLanguage?.text = movie.language
        binding?.content?.tvOverview?.text = movie.overview

        var isFavorite = movie.isFavorite
        setStateFavorite(isFavorite)
        binding?.fabFavorite?.setOnClickListener {
            isFavorite = !isFavorite
            detailViewModel.setFavoriteMovie(movie, isFavorite)
            setStateFavorite(isFavorite)
        }
    }

    private fun setStateFavorite(state: Boolean) {
        if (state) {
            binding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailBinding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}