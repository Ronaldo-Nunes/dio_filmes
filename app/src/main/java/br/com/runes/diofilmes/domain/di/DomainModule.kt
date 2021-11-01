package br.com.runes.diofilmes.domain.di

import br.com.runes.diofilmes.domain.DetailMovieUseCase
import br.com.runes.diofilmes.domain.ListMoviesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { ListMoviesUseCase(get()) }
            factory { DetailMovieUseCase(get()) }
        }
    }

}