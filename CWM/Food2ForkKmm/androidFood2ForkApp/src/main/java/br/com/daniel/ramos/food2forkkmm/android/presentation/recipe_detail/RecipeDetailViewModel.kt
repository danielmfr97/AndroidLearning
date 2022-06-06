package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeService
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val recipeService: RecipeService) : ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        getRecipeId()
    }

    private fun getRecipeId() {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                recipe.value = recipeService.get(recipeId)
            }
        }
    }
}