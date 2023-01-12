package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.Composable
import br.com.daniel.ramos.food2forkkmm.android.presentation.components.RecipeList
import br.com.daniel.ramos.food2forkkmm.android.presentation.theme.AppTheme
import br.com.daniel.ramos.food2forkkmm.presentation.recipe_list.RecipeListEvents
import br.com.daniel.ramos.food2forkkmm.presentation.recipe_list.RecipeListState

@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading
    ) {
        RecipeList(
            isLoading = state.isLoading,
            recipes = state.recipes,
            page = state.page,
            onTriggerNextPage = {
                onTriggerEvent(RecipeListEvents.NextPage)
            },
            onClickRecipeListItem = onClickRecipeListItem
        )
    }
}