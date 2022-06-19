package br.com.daniel.ramos.food2forkkmm.android.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeList(
    isLoading: Boolean,
    recipes: List<Recipe>,
    onClickRecipeListItem: (Int) -> Unit,
) {
    if (isLoading && recipes.isEmpty()){
        // Loading
    } else if (recipes.isEmpty()) {
        // Nothing to show.. no recipes
    } else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) {
                    index, recipe ->
                RecipeCard(recipe = recipe) {
                    onClickRecipeListItem(recipe.id)
                }
            }
        }
    }
}