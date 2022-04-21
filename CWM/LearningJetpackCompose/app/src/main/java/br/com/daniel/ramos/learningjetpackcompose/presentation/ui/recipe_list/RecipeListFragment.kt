package br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.FoodCategoryChip
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @ExperimentalUnitApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val focusManager = LocalFocusManager.current
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value

                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        elevation = 8.dp
                    ) {
                        Column {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    value = query,
                                    onValueChange = { newValue ->
                                        viewModel.onQueryChanged(newValue)
                                    },
                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search,
                                        autoCorrect = true,
                                    ),
                                    keyboardActions = KeyboardActions(onDone = {
                                        viewModel.newSearch()
                                        focusManager.clearFocus()
                                    }),
                                    leadingIcon = {
                                        Icon(Icons.Filled.Search, "")
                                    },
                                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
                                )
                            }
                            val lazyListState = rememberLazyListState()
                            val coroutineScope = rememberCoroutineScope()
                            LazyRow(
                                state = lazyListState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp, bottom = 8.dp),

                                content = {
                                    coroutineScope.launch {
                                        lazyListState.scrollToItem(
                                            viewModel.categoryScrollPosition,
                                            viewModel.categoryScrollOffSetPosition
                                        )
                                    }
                                    items(getAllFoodCategories()) { category ->
                                        FoodCategoryChip(
                                            category = category.value,
                                            isSelected = selectedCategory == category,
                                            onSelectedCategoryChanged = {
                                                viewModel.onSelectedCategoryChanged(it)
                                                viewModel.categoryScrollPosition =
                                                    lazyListState.firstVisibleItemIndex
                                                viewModel.categoryScrollOffSetPosition =
                                                    lazyListState.firstVisibleItemScrollOffset
                                            },
                                            onExecuteSearch = viewModel::newSearch
                                        )
                                    }
                                })
                        }
                    }
                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {})
                        }
                    }
                }
            }
        }
    }
}