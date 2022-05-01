package br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.daniel.ramos.learningjetpackcompose.presentation.BaseApplication
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.CircularIndeterminateProgressBar
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.RecipeCard
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.SearchAppBar
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.ShimmerRecipeCardItem
import br.com.daniel.ramos.learningjetpackcompose.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val viewModel: RecipeListViewModel by viewModels()


     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                AppTheme(darkTheme = application.isDark.value) {
                    val focusManager = LocalFocusManager.current
                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val selectedCategory = viewModel.selectedCategory.value
                    val loading = viewModel.loading.value

                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                focusManager = focusManager,
                                query = query,
                                onQueryChange = viewModel::onQueryChanged,
                                onExecuteSearch = viewModel::newSearch,
                                categoryScrollPosition = viewModel.categoryScrollPosition,
                                categoryScrollOffSetPosition = viewModel.categoryScrollOffSetPosition,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChange = viewModel::onSelectedCategoryChanged,
                                onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                                onChangeCategoryScrollOffSetPosition = viewModel::onChangeCategoryScrollOffSetPosition,
                                onToggleTheme = {
                                    application.toogleLightTheme()
                                }
                            )
                        },
                        bottomBar = {},
                        drawerContent = {},
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.surface)
                        ) {
                            if (loading) {
                                ShimmerRecipeCardItem(
                                    imageHeight = 250.dp, padding = 8.dp
                                )
                            } else {
                                LazyColumn(modifier = Modifier.padding(8.dp)) {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onClick = {})
                                    }
                                }
                                CircularIndeterminateProgressBar(isDisplayed = loading)
                            }
                        }
                    }
                }
            }
        }
    }
}