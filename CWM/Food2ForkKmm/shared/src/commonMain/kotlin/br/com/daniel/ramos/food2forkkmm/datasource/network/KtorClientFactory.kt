package br.com.daniel.ramos.food2forkkmm.datasource.network

import br.com.daniel.ramos.food2forkkmm.datasource.network.model.RecipeDto
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}

val datetimeUtil = DatetimeUtil()

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe> {
    return map{it.toRecipe()}
}

