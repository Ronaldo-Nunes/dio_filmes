package br.com.runes.diofilmes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.runes.diofilmes.data.model.Movie
import br.com.runes.diofilmes.domain.DetailMovieUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(private val detailUseCase: DetailMovieUseCase) : ViewModel() {

    private val _movie = MutableLiveData<State>()
    val movie: LiveData<State> = _movie

    fun getMovie(movieId: Long) {
        viewModelScope.launch {
            detailUseCase(movieId)
                .onStart {
                    _movie.postValue(State.Loading)
                }
                .catch {
                    _movie.postValue(State.Error(it))
                }
                .collect {
                    _movie.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val movie: Movie) : State()
        data class Error(val error: Throwable) : State()
    }
}