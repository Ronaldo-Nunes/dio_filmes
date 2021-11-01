package br.com.runes.diofilmes.data.services

import br.com.runes.diofilmes.data.services.ApiPath.API_KEY
import br.com.runes.diofilmes.data.services.ApiPath.DEFAULT_LANGUAGE
import br.com.runes.diofilmes.data.model.Movie
import br.com.runes.diofilmes.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbService {
    @GET("popular?api_key=$API_KEY&$DEFAULT_LANGUAGE")
    suspend fun listAllMovies() : MovieResponse

    @GET("{movie_id}?api_key=$API_KEY&$DEFAULT_LANGUAGE")
    suspend fun showMovieDetail(@Path("movie_id") movieId: Long): Movie
}