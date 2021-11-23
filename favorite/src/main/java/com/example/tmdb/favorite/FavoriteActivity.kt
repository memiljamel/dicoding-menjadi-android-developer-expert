package com.example.tmdb.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.core.domain.model.Movie
import com.example.tmdb.core.ui.ListMovieAdapter
import com.example.tmdb.detail.DetailActivity
import com.example.tmdb.di.FavoriteModuleDependencies
import com.example.tmdb.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        viewModelFactory
    }

    private var _activityFavoriteBinding: ActivityFavoriteBinding? = null
    private val binding get() = _activityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        _activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        favoriteViewModel.favorite.observe(this, { movies ->
            showListMovie(movies)
            binding?.tvErrorMessage?.visibility =
                if (movies.isNotEmpty()) View.GONE else View.VISIBLE
        })
    }

    private fun showListMovie(movies: List<Movie>?) {
        val adapter = ListMovieAdapter()
        binding?.rvMovieList?.layoutManager = LinearLayoutManager(this)
        adapter.setData(movies)
        binding?.rvMovieList?.adapter = adapter

        adapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movie)
                startActivity(intent)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoriteBinding = null
    }
}