package br.com.runes.diofilmes.data.repositories

import br.com.runes.diofilmes.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun listMovies(): Flow<List<Movie>>

    suspend fun getMovie(movieId: Long): Flow<Movie>
}