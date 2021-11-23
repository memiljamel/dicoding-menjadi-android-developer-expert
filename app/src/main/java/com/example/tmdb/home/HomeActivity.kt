package com.example.tmdb.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.core.data.Resource
import com.example.tmdb.core.domain.model.Movie
import com.example.tmdb.core.ui.ListMovieAdapter
import com.example.tmdb.databinding.ActivityHomeBinding
import com.example.tmdb.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    private var _activityHomeBinding: ActivityHomeBinding? = null
    private val binding get() = _activityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        homeViewModel.movies.observe(this, { movies ->
            when (movies) {
                is Resource.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    showListMovie(movies.data)
                }
                is Resource.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.tvErrorMessage?.visibility = View.VISIBLE
                    binding?.tvErrorMessage?.text =
                        movies.message ?: resources.getString(R.string.message)
                }
            }
        })
    }

    private fun showListMovie(movies: List<Movie>?) {
        val adapter = ListMovieAdapter()
        binding?.rvMovieList?.layoutManager = LinearLayoutManager(this)
        adapter.setData(movies)
        binding?.rvMovieList?.adapter = adapter

        adapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movie)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                val uri = Uri.parse("tmdb://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityHomeBinding = null
    }
}