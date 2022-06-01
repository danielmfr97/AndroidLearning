package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val recipeId: MutableState<Int?> = mutableStateOf(null)

    init {
        getRecipeId()
    }

    private fun getRecipeId() {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            this.recipeId.value = recipeId
        }
    }
}