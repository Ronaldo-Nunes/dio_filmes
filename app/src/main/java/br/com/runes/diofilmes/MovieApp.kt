package br.com.runes.diofilmes

import android.app.Application
import br.com.runes.diofilmes.data.di.DataModule
import br.com.runes.diofilmes.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
        }

        DataModule.load()
        DomainModule.load()
//        PresentationModule.load()
    }
}