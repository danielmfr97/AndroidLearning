package br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.daniel.ramos.learningjetpackcompose.domain.model.Recipe
import br.com.daniel.ramos.learningjetpackcompose.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val PAGE_SIZE = 30

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    val page = mutableStateOf(1)

    private var recipeListScrollPosition = 0

    var categoryScrollPosition: Int = 0

    var categoryScrollOffSetPosition: Int = 0

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            delay(2000) // Simulando um delay na rede
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result

            loading.value = false
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            // Prevent duplicate events due to recompose happening to quickly
            if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                loading.value = true
                incrementPage()

                delay(1000) //Just to show how fast pagination api is

                if (page.value > 1) {
                    val result = repository.search(token = token, page = page.value, query = query.value)
                    appendRecipes(result)
                }
                loading.value = false
            }
        }
    }

    /**
     * Append new recipes to the current list of recipes
     */

    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(this.recipes.value)
        current.addAll(recipes)
        this.recipes.value = current
    }

    private fun incrementPage() {
        page.value += 1
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        recipeListScrollPosition = position
    }

    private fun resetSearchState() {
        recipes.value = listOf()
        page.value = 1
        onChangeRecipeScrollPosition(0)
        if (selectedCategory.value?.value != query.value)
            clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Int) {
        categoryScrollPosition = position
    }

    fun onChangeCategoryScrollOffSetPosition(position: Int) {
        categoryScrollOffSetPosition = position
    }
}
