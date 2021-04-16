package app.bo.com.ucb.framework

import app.bo.com.ucb.data.IRemoteDataSource
import app.bo.com.ucb.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource(val apiRest: RetrofitBuilder): IRemoteDataSource {
    override suspend fun getPopularMovies(apiKey: String): List<Movie> {
        return withContext(Dispatchers.IO) {
            val response = apiRest.apiService.listPopularMovies(apiKey).results.map {
                it.toDomainMovie()
            }
            response
        }

    }
}