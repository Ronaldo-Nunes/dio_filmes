package br.com.runes.diofilmes.services

import br.com.runes.diofilmes.services.Resources.API_KEY
import br.com.runes.diofilmes.services.Resources.DEFAULT_LANGUAGE
import br.com.runes.diofilmes.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbService {
    @GET("popular?api_key=$API_KEY&$DEFAULT_LANGUAGE")
    suspend fun listAllMovies() : List<Movie>

    @GET("{movie_id}?api_key=$API_KEY&$DEFAULT_LANGUAGE")
    suspend fun showMovieDetail(@Path("movie_id") movieId: Long): Movie
}