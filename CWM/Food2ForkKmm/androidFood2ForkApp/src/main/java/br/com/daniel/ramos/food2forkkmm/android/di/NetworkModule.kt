package br.com.daniel.ramos.food2forkkmm.android.di

import android.os.Build.VERSION_CODES.BASE
import br.com.daniel.ramos.food2forkkmm.datasource.network.KtorClientFactory
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeService
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeServiceImpl
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(
        httpClient: HttpClient,): RecipeService {
        return RecipeServiceImpl(
            httpClient = httpClient,
            baseUrl = BASE_URL
        )
    }
}