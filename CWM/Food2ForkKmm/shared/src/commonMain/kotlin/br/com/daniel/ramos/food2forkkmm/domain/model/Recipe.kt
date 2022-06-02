package br.com.daniel.ramos.food2forkkmm.domain.model

import kotlinx.datetime.LocalDateTime

data class Recipe(
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int,
    val sourceUrl: String,
    val ingredients: List<String> = listOf(),
    val dateAdded: LocalDateTime, // Shared kotlin datelibrary used to handle all of our dates
    val dateUpdated: LocalDateTime, // Shared kotlin datelibrary used to handle all of our dates
    )
 