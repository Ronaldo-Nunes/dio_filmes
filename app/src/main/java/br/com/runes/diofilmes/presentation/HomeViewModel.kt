package br.com.runes.diofilmes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.runes.diofilmes.core.UseCase
import br.com.runes.diofilmes.data.model.Movie
import br.com.runes.diofilmes.domain.ListMoviesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listMoviesUseCase: ListMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<State>()
    val movies: LiveData<State> = _movies

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            listMoviesUseCase(UseCase.None)
                .onStart {
                    _movies.postValue(State.Loading)
                }
                .catch {
                    _movies.postValue(State.Error(it))
                }
                .collect {
                    _movies.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: List<Movie>) : State()
        data class Error(val error: Throwable) : State()
    }

}