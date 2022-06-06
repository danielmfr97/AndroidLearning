package br.com.daniel.ramos.food2forkkmm.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_list.RecipeListViewModel
import br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_detail.RecipeDetailViewModel
import br.com.daniel.ramos.food2forkkmm.android.presentation.recipe_list.RecipeListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {

        //=========== Recipe List Screen ===========//
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry )
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
            RecipeListScreen(onSelectedRecipe = { recipeId ->
                navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
            })
        }
        //=========== Recipe Detail Screen ===========//
        composable(
            route = Screen.RecipeList.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry )
            val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
            RecipeDetailScreen(recipe = viewModel.recipe  .value )
        }
    }
}