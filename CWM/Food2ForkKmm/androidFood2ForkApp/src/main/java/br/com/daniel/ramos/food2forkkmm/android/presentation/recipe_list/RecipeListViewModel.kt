package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, // dont need for this VM
    private val searchRecipes: SearchRecipes
) : ViewModel() {
    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = 1,
            query = "chicken"
        ).onEach { dataState ->
            println("RecipeListViewmodel ${dataState.isLoading}")

            dataState.data?.let { recipes ->
                println("RecipeListViewmodel $recipes")
            }

            dataState.message?.let { message ->
                println("RecipeListViewmodel $message")
            }
        }.launchIn(viewModelScope)
    }
}