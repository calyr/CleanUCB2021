package app.bo.com.ucb.cleanucb2021

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.bo.com.ucb.domain.Movie
import app.bo.com.ucb.usecases.GetPopularMovies
import kotlinx.coroutines.launch

class MainViewModel(val popularMovies: GetPopularMovies): ScopedViewModel() {

    init {
        initScope()
    }

    private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get() = _model

    sealed class UiModel{
        class Content(val movies: List<Movie>): UiModel()
    }

    fun loadMovies() {

        launch {
            _model.value = UiModel.Content(popularMovies.invoke())
        }

    }
}