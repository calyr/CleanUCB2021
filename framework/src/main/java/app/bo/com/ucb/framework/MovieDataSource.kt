package app.bo.com.ucb.framework

import app.bo.com.ucb.data.IRemoteDataSource
import app.bo.com.ucb.domain.Movie

class MovieDataSource(val apiRest: RetrofitBuilder): IRemoteDataSource {
    override suspend fun getPopularMovies(apiKey: String): List<Movie> {
        val response = apiRest.apiService.listPopularMovies(apiKey).result.map {
            it.toDomainMovie()
        }
        return response
    }
}