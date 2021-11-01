package br.com.runes.diofilmes.domain

import br.com.runes.diofilmes.core.UseCase
import br.com.runes.diofilmes.data.model.Movie
import br.com.runes.diofilmes.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class DetailMovieUseCase(private val repository: MovieRepository) : UseCase<Long, Movie>() {
    override suspend fun execute(param: Long): Flow<Movie> {
        return repository.getMovie(movieId = param)
    }
}