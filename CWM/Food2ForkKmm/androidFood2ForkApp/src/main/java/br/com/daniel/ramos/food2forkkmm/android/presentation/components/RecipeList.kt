package br.com.daniel.ramos.food2forkkmm.android.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeList(
    isLoading: Boolean,
    recipes: List<Recipe>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    if (isLoading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
    } else if (recipes.isEmpty()) {
        // Nothing to show.. no recipes
    } else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                // Ex.: Index = 30, page = 1. 31 > 30.
                if ((index + 1) >= (page * RECIPE_PAGINATION_PAGE_SIZE) && !isLoading) {
                    onTriggerNextPage()
                }
                RecipeCard(recipe = recipe) {
                    onClickRecipeListItem(recipe.id)
                }
            }
        }
    }
}