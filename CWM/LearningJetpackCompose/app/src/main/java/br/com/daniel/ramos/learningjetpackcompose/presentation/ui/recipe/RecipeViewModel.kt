package br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.learningjetpackcompose.domain.model.Recipe
import br.com.daniel.ramos.learningjetpackcompose.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_RECIPE = "recipe.state.recipe.key"

private const val TAG = "RecipeViewModel"

@HiltViewModel
class RecipeViewModel
@Inject constructor(
    private val recipeRepository: RecipeRepository,
    private @Named("auth_token") val token: String,
    private val state: SavedStateHandle,
): ViewModel(){

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    init {
        // restore if process dies
        state.get<Int>(STATE_KEY_RECIPE)?.let{ recipeId ->
            onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is RecipeEvent.GetRecipeEvent -> {
                        if(recipe.value == null){
                            getRecipe(event.id)
                        }
                    }
                }
            }catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private suspend fun getRecipe(id: Int){
        loading.value = true

        // simulate a delay to show loading
        delay(1000)

        val recipe = recipeRepository.get(token=token, id=id)
        this.recipe.value = recipe

        state.set(STATE_KEY_RECIPE, recipe.id)

        loading.value = false
    }
}