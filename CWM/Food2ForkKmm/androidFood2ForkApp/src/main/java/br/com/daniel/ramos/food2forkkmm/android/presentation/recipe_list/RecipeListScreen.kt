package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.Composable
import br.com.daniel.ramos.food2forkkmm.android.presentation.components.RecipeList
import br.com.daniel.ramos.food2forkkmm.android.presentation.theme.AppTheme
import br.com.daniel.ramos.food2forkkmm.presentation.recipe_list.RecipeListState

@Composable
fun RecipeListScreen(
    state: RecipeListState,
      onClickRecipeListItem: (Int) -> Unit,
){
    AppTheme(
        displayProgressBar = state.isLoading
    ) {
        RecipeList(
            isLoading = state.isLoading,
            recipes = state.recipes,
            onClickRecipeListItem = onClickRecipeListItem
        )
    }
}