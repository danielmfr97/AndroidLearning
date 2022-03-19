package br.com.daniel.ramos.learningjetpackcompose.di

import br.com.daniel.ramos.learningjetpackcompose.network.RecipeService
import br.com.daniel.ramos.learningjetpackcompose.network.model.RecipeDtoMapper
import br.com.daniel.ramos.learningjetpackcompose.repository.RecipeRepository
import br.com.daniel.ramos.learningjetpackcompose.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            recipeService,
            recipeDtoMapper
        )
    }
}