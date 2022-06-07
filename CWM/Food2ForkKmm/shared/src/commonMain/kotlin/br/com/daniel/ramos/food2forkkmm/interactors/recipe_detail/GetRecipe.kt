package br.com.daniel.ramos.food2forkkmm.interactors.recipe_detail

import br.com.daniel.ramos.food2forkkmm.datasource.cache.RecipeCache
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache
) {
    /**
     * No caso de uso SearchRecipes, sabemos que todas as receitas vão estar em Cache antes de buscarmos informações de uma em específico
     * então é 100% de certeza que os dados estarão em cache e não precisaremos busca-los novamente
     */
    fun execute(recipeId: Int,): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading<Recipe>())

            val recipe = recipeCache.get(recipeId = recipeId)

            emit(DataState.data<Recipe>(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = e.message?: "unknow error"))
        }
    }
}