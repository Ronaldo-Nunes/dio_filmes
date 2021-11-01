package br.com.runes.diofilmes.repositories

import br.com.runes.diofilmes.core.RemoteException
import br.com.runes.diofilmes.services.TheMovieDbService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieRepositoryImpl(private val service: TheMovieDbService) : MovieRepository {

    override suspend fun listMovies() = flow {
        try {
            val movieList = service.listAllMovies()
            emit(movieList)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possivel fazer a busca no momento!")
        }
    }

    override suspend fun getMovie(movieId: Long) = flow {
        try {
            val movie = service.showMovieDetail(movieId)
            emit(movie)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possivel fazer a busca no momento!")
        }
    }
}