package app.bo.com.ucb.framework

import app.bo.com.ucb.framework.server.Movie

class MovieResponse(val page: Int, val results: List<Movie>)