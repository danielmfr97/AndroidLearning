package br.com.daniel.ramos.learningjetpackcompose.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list.FoodCategory
import br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list.getAllFoodCategories
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(
    focusManager: FocusManager,
    query: String,
    onQueryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categoryScrollPosition: Int,
    categoryScrollOffSetPosition: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChange: (String) -> Unit,
    onChangeCategoryScrollPosition: (Int) -> Unit,
    onChangeCategoryScrollOffSetPosition: (Int) -> Unit,
    onToggleTheme: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
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
                        onQueryChange(newValue)
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
                        onExecuteSearch()
                        focusManager.clearFocus()
                    }),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, "")
                    },
                    textStyle = MaterialTheme.typography.button,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
                )
                ConstraintLayout(modifier = Modifier.align(Alignment.CenterVertically)) {
                    val menu = createRef()
                    IconButton(onClick = { onToggleTheme() }, modifier = Modifier.constrainAs(menu) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Icon(Icons.Filled.MoreVert, "MoreVert")
                    }
                }
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
                            categoryScrollPosition,
                            categoryScrollOffSetPosition
                        )
                    }
                    items(getAllFoodCategories()) { category ->
                        FoodCategoryChip(
                            category = category.value,
                            isSelected = selectedCategory == category,
                            onSelectedCategoryChanged = {
                                onSelectedCategoryChange(it)
                                onChangeCategoryScrollPosition(lazyListState.firstVisibleItemIndex)
                                onChangeCategoryScrollOffSetPosition(lazyListState.firstVisibleItemScrollOffset)
                            },
                            onExecuteSearch = { onExecuteSearch() }
                        )
                    }
                })
        }
    }
}