package br.com.daniel.ramos.food2forkkmm.datasource.network

import br.com.daniel.ramos.food2forkkmm.domain.model.Recipe

interface RecipeService {

    suspend fun search(
        page: Int,
        query: String,
    ): List<Recipe>

    suspend fun get(
        id: Int,
    ): Recipe
}