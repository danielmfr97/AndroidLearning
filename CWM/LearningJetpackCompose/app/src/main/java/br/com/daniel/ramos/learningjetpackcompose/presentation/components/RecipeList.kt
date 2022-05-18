package br.com.daniel.ramos.learningjetpackcompose.presentation.components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.daniel.ramos.learningjetpackcompose.R
import br.com.daniel.ramos.learningjetpackcompose.domain.model.Recipe
import br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list.DefaultSnackbar
import br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list.PAGE_SIZE
import br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list.RecipeListEvent
import br.com.daniel.ramos.learningjetpackcompose.presentation.util.SnackbarController
import kotlinx.coroutines.launch

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeRecipeScrollPosition: (Int) -> Unit,
    page: Int,
    onTriggerEvent: (RecipeListEvent) -> Unit,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && recipes.isEmpty()) {
            ShimmerRecipeCardItem(
                imageHeight = 250.dp, padding = 8.dp
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(
                    8.dp
                )
            ) {
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    onChangeRecipeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE)) {
                       onTriggerEvent(RecipeListEvent.NextPageEvent)
                    }
                    RecipeCard(recipe = recipe, onClick = {
                        if(recipe.id != null){
                            val bundle = Bundle()
                            bundle.putInt("recipeId", recipe.id)
                            navController.navigate(R.id.action_recipeListFragment_to_recipeFragment, bundle)
                        } else {
                            snackbarController.getScope().launch { snackbarController.showSnackbar(scaffoldState = scaffoldState, message = "Recipe Error", actionLabel = "Ok") }
                        }
                    })
                }
            }
        }
    }
}