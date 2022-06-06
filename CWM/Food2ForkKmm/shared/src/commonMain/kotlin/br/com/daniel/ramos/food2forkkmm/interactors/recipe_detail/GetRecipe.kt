package br.com.daniel.ramos.food2forkkmm.interactors.recipe_detail

import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeService
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeService: RecipeService
) {
    fun execute(recipeId: Int,): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())

        try {
            val recipe = recipeService.get(id = recipeId)
            emit(DataState.data(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message?: "unknow error"))
        }
    }
}