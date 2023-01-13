package br.com.daniel.ramos.food2forkkmm.interactors.recipe_list

import br.com.daniel.ramos.food2forkkmm.datasource.cache.RecipeCache
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeService
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading<List<Recipe>>())
        try {
            val recipes = recipeService.search(
                page = page,
                query = query
            )
            // Delay 500ms to see loading
            delay(500)

            delay(5000)

            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(query = query, page = page)
            }
            emit(DataState.data<List<Recipe>>(data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(message = e.message ?: "Unknow error"))
        }
    }

}
