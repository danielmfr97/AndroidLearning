package br.com.daniel.ramos.food2forkkmm.android.di

import br.com.daniel.ramos.food2forkkmm.android.BaseApplication
import br.com.daniel.ramos.food2forkkmm.datasource.cache.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase = RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()

    @Singleton
    @Provides
    fun provideRecipeCache(recipeDatabase: RecipeDatabase): RecipeCache = RecipeCacheImpl(recipeDatabase = recipeDatabase)
    
}