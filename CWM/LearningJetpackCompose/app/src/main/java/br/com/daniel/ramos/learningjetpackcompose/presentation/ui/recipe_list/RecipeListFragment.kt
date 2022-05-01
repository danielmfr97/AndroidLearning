package br.com.daniel.ramos.learningjetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.daniel.ramos.learningjetpackcompose.presentation.BaseApplication
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.CircularIndeterminateProgressBar
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.RecipeCard
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.SearchAppBar
import br.com.daniel.ramos.learningjetpackcompose.presentation.components.ShimmerRecipeCardItem
import br.com.daniel.ramos.learningjetpackcompose.presentation.theme.AppTheme
import br.com.daniel.ramos.learningjetpackcompose.presentation.util.SnackbarController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val viewModel: RecipeListViewModel by viewModels()
    private val snackbarController = SnackbarController(lifecycleScope)


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
                    val scaffoldState = rememberScaffoldState()

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
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
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
                                LazyColumn(
                                    modifier = Modifier.padding(
                                        8.dp
                                    )
                                ) {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onClick = {})
                                    }
                                }
                                CircularIndeterminateProgressBar(isDisplayed = loading)
                                DefaultSnackbar(
                                    snackbarHostState = scaffoldState.snackbarHostState,
                                    modifier = Modifier.align(
                                        Alignment.BottomCenter
                                    )
                                ) {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //    Simple-way to make snackbar
    @Composable
    fun SnackbarDemo(isShowing: Boolean, onHideSnackbar: () -> Unit) {
        if (isShowing) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val snackbar = createRef()
                Snackbar(modifier = Modifier.constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                    action = {
                        Text(
                            text = "Hide",
                            modifier = Modifier.clickable(onClick = onHideSnackbar),
                            style = MaterialTheme.typography.h5
                        )
                    }) {
                    Text(text = "Hey i'm a snackbar")
                }
            }
        }
    }

    @Composable
    fun testSnackBar() {
        val isShowing = remember { mutableStateOf(false) }
        Column {
            Button(onClick = { isShowing.value = true }) {
                Text(text = "Show SnackBar")
            }
            SnackbarDemo(isShowing = isShowing.value) {
                isShowing.value = false
            }
        }
    }

    //    Use Snackbarhoststate, kind like manager animation, queue system, etc.
    @Composable
    fun DecoupledSnackbarDemo(snackbarHostState: SnackbarHostState) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val snackbar = createRef()
            SnackbarHost(
                modifier = Modifier.constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                hostState = snackbarHostState,
                snackbar = {
                    Snackbar(action = {
                        TextButton(
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        ) {
                            Text(
                                text = snackbarHostState.currentSnackbarData?.actionLabel ?: "Hide",
                                style = TextStyle(color = Color.White)
                            )
                        }
                    }) {
                        Text(
                            text = snackbarHostState.currentSnackbarData?.message
                                ?: "Hey look a snackbar"
                        )
                    }
                },
            )
        }
    }

    @Composable
    fun testSnackBarHostState() {
        val snackbarHostSate = remember { SnackbarHostState() }
        Column {
            Button(onClick = {
                lifecycleScope.launch {
                    snackbarHostSate.showSnackbar(
                        message = "Hey look a snack",
                        actionLabel = "Hi",
                        duration = SnackbarDuration.Short
                    )
                }
            }
            ) {
                Text(text = "Show SnackBar")
            }
            DecoupledSnackbarDemo(snackbarHostState = snackbarHostSate)
        }
    }
}