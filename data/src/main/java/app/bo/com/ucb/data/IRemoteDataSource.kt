package app.bo.com.ucb.data

import app.bo.com.ucb.domain.Movie

interface IRemoteDataSource {
    suspend fun getPopularMovies(apiKey: String): List<Movie>
}