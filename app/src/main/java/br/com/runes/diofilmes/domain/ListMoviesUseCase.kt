package br.com.runes.diofilmes.domain

import br.com.runes.diofilmes.core.UseCase
import br.com.runes.diofilmes.core.UseCase.None
import br.com.runes.diofilmes.data.model.Movie
import br.com.runes.diofilmes.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class ListMoviesUseCase(
    private val repository: MovieRepository
) : UseCase<None, List<Movie>>() {

    override suspend fun execute(param: None): Flow<List<Movie>> {
        return repository.listMovies()
    }

}