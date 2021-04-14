package app.bo.com.ucb.cleanucb2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.bo.com.ucb.data.MoviesRepository
import app.bo.com.ucb.framework.MovieDataSource
import app.bo.com.ucb.framework.RetrofitBuilder
import app.bo.com.ucb.usecases.GetPopularMovies
import okhttp3.Cache.Companion.key

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(GetPopularMovies(MoviesRepository( MovieDataSource(RetrofitBuilder), getString(R.string.key) )))
    }
}