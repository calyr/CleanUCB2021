package app.bo.com.ucb.usecases

import app.bo.com.ucb.data.MoviesRepository
import app.bo.com.ucb.domain.Movie

class GetPopularMovies(val repository: MoviesRepository) {
    fun invoke(): List<Movie> {
        return repository.getPopularMovies()
    }
}