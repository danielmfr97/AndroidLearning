package br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import br.com.daniel.ramos.food2forkkmm.android.presentation.theme.AppTheme
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(recipe: Recipe?,) {
    AppTheme(displayProgressBar = false) {
        if(recipe == null)
            Text("ERROR") else Text("RecipeDEtailScreen: ${recipe.title}")
    }
}