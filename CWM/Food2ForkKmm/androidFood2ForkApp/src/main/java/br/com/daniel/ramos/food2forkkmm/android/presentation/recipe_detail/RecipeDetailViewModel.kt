package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.interactors.recipe_detail.GetRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            getRecipe(recipeId = recipeId)
        }
    }

    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            println("RecipeDetailVM: loading: ${dataState.isLoading}")

            dataState.data?.let { recipe ->
                println("RecipeDetailVM: recipe: ${recipe}")
                this.recipe.value = recipe
            }

            dataState.message?.let { message ->
                println("RecipeDetailVM: error: ${message}")
            }
        }.launchIn(viewModelScope)
    }
}
