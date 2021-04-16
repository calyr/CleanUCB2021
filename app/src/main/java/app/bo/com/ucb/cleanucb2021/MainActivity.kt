package app.bo.com.ucb.cleanucb2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bo.com.ucb.data.MoviesRepository
import app.bo.com.ucb.domain.Movie
import app.bo.com.ucb.framework.MovieDataSource
import app.bo.com.ucb.framework.RetrofitBuilder
import app.bo.com.ucb.usecases.GetPopularMovies
import okhttp3.Cache.Companion.key

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(GetPopularMovies(MoviesRepository( MovieDataSource(RetrofitBuilder), getString(R.string.key) )))
        recyclerView = findViewById(R.id.recyclerView)

        val layoutManager = GridLayoutManager(this, 3)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        mainViewModel.model.observe(this, Observer(::updateUi))

        mainViewModel.loadMovies()
    }

    private fun updateUi(uiModel: MainViewModel.UiModel) {
        when( uiModel) {
            is MainViewModel.UiModel.Content -> loadMovies( uiModel.movies)
        }

    }

    private fun loadMovies(movies: List<Movie>) {
        recyclerView.adapter = MainAdapter(movies)
    }
}