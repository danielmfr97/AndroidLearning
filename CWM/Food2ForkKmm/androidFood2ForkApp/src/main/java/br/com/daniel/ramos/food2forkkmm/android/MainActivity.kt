package br.com.daniel.ramos.food2forkkmm.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.daniel.ramos.food2forkkmm.android.presentation.navigation.Navigation
import br.com.daniel.ramos.food2forkkmm.datasource.network.KtorClientFactory
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeServiceImpl
import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.BASE_URL
import br.com.daniel.ramos.food2forkkmm.domain.util.DatetimeUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ktorClient = KtorClientFactory().build()
        CoroutineScope(IO).launch {
            val recipeId= 1551
            val recipeService = RecipeServiceImpl(httpClient = ktorClient, baseUrl = BASE_URL)
            val recipe = recipeService.get(recipeId)
            println("KtorTesT: ${recipe.title}")
            println("KtorTesT: ${recipe.ingredients}")
            println("KtorTesT: ${DatetimeUtil().humanizeDatetime(recipe.dateUpdated)}")
        }
        setContent {
            Navigation()
        }
    }
}
