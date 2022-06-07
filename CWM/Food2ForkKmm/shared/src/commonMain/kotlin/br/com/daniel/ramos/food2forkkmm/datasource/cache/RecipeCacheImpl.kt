package br.com.daniel.ramos.food2forkkmm.datasource.cache

import br.com.daniel.ramos.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe
import br.com.daniel.ramos.food2forkkmm.domain.util.DatetimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
) : RecipeCache {
    private val queries: RecipeDbQueries =
        recipeDatabase.recipeDbQueries // Class generate by sqlight to work with our queries

    override fun insert(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients = recipe.ingredients.convertIngredientListToString(),
            date_added = DatetimeUtil.toEpochMilliseconds(recipe.dateAdded),
            date_updated = DatetimeUtil.toEpochMilliseconds(recipe.dateUpdated)
        )
    }

    override fun insert(recipes: List<Recipe>) {
        recipes.map { insert(it) }
    }

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = ((page - 1) * RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Recipe? {
       return try {
            queries.getRecipeById(id = recipeId.toLong()).executeAsOne().toRecipe()
       } catch (e: NullPointerException) {
           null
       }
    }
}