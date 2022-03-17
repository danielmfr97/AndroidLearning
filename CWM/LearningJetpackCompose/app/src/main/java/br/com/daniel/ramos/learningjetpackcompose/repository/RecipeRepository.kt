package br.com.daniel.ramos.learningjetpackcompose.repository

import br.com.daniel.ramos.learningjetpackcompose.domain.model.Recipe

interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    suspend fun get(token: String, id: Int): Recipe

}