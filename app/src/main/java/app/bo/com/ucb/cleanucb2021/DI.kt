package app.bo.com.ucb.cleanucb2021

import app.bo.com.ucb.data.IRemoteDataSource
import app.bo.com.ucb.data.MoviesRepository
import app.bo.com.ucb.framework.MovieDataSource
import app.bo.com.ucb.framework.RetrofitBuilder
import app.bo.com.ucb.usecases.GetPopularMovies
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun AndroidApplication.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(scopeModule, dataModule, frameworkModule, appModule))
    }
}

private val scopeModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get()) }
        scoped { GetPopularMovies(get()) }
    }
}

private val dataModule = module {
    factory { MoviesRepository(get(), get(named("apiKey")))  }
    factory<IRemoteDataSource> { MovieDataSource(get())  }
}


private val frameworkModule = module {
    factory { RetrofitBuilder }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.key) }
}


