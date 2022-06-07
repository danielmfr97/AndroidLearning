package br.com.daniel.ramos.food2forkkmm.datasource.cache

import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.domain.util.DatetimeUtil

/***
 * Essas extensões vão ser usadas para fazer as conversões dos nossos objetos para o banco de dados e vice-versa
 */


fun Recipe_Entity.toRecipe(): Recipe {
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsToList(),
        dateAdded = DatetimeUtil.toLocalDate(date_added),
        dateUpdated = DatetimeUtil.toLocalDate(date_updated)
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
    return map{it.toRecipe()}
}

/**
 * Como o SQLDelight não salva lista de objetos e sim de Strings, precisamos que essas extensões o façam para nós
 */
fun List<String>.convertIngredientListToString(): String {
    val ingredientsString = StringBuilder()
    this.onEach { ingredient ->
        ingredientsString.append("$ingredient,")
    }
    return ingredientsString.toString()
}

fun String.convertIngredientsToList(): List<String> {
    val list = ArrayList<String>()
    for (ingredient in split(","))
        list.add(ingredient)
    return list
}
