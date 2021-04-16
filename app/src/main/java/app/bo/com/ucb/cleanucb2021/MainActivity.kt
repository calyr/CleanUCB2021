package app.bo.com.ucb.cleanucb2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.bo.com.ucb.cleanucb2021.adapter.MainAdapter
import app.bo.com.ucb.data.MoviesRepository
import app.bo.com.ucb.domain.Movie
import app.bo.com.ucb.framework.MovieDataSource
import app.bo.com.ucb.framework.RetrofitBuilder
import app.bo.com.ucb.usecases.GetPopularMovies
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    //lateinit var mainViewModel: MainViewModel
    private val mainViewModel: MainViewModel by currentScope.viewModel(this)
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        val layoutManager = GridLayoutManager(this, 3 )
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        //mainViewModel = MainViewModel(GetPopularMovies(MoviesRepository( MovieDataSource(RetrofitBuilder), getString(R.string.key) )))
        mainViewModel.model.observe( this, Observer(::updateUi))
        mainViewModel.loadMovies()


    }

    private fun updateUi(uiModel: MainViewModel.UiModel) {
        when ( uiModel) {
            is MainViewModel.UiModel.Content -> {
                loadData(  uiModel.movies )
            }
        }
    }

    private fun loadData(movies: List<Movie>) {
        recyclerView.adapter = MainAdapter(movies)
    }
}