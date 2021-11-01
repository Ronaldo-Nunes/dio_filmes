package br.com.runes.diofilmes.presentation.di

import br.com.runes.diofilmes.presentation.DetailViewModel
import br.com.runes.diofilmes.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { HomeViewModel(get()) }
            viewModel { DetailViewModel(get()) }
        }
    }
}