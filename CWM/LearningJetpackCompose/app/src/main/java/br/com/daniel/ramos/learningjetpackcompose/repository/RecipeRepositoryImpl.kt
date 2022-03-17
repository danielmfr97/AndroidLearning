package br.com.daniel.ramos.learningjetpackcompose.repository

import br.com.daniel.ramos.learningjetpackcompose.domain.model.Recipe
import br.com.daniel.ramos.learningjetpackcompose.network.RecipeService
import br.com.daniel.ramos.learningjetpackcompose.network.model.RecipeDtoMapper

class RecipeRepositoryImpl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val result = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token = token, id))
    }
}