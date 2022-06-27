package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.interactors.recipe_list.SearchRecipes
import br.com.daniel.ramos.food2forkkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, // dont need for this VM
    private val searchRecipes: SearchRecipes
) : ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())
    init {
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appeendRecipes(recipes)
            }

            dataState.message?.let { message ->
                println("RecipeListVM: error: ${message}")
            }
        }.launchIn(viewModelScope)
    }

    private fun appeendRecipes(recipes: List<Recipe>) {
        println("RecipeListVM: recipes: ${recipes}")
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }
}
