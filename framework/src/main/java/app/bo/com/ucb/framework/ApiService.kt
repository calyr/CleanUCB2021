package app.bo.com.ucb.framework

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMovies(@Query("api_key") apiKey: String): MovieResponse
}