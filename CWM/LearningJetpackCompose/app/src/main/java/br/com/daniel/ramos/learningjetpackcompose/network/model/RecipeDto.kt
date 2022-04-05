package br.com.daniel.ramos.learningjetpackcompose.network.model

import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("cooking_instructions")
    val cookingInstructions: String? = null,

    @SerializedName("date_added")
    val dateAdded: String? = null,

    @SerializedName("date_updated")
    val dateUpdated: String? = null,

    val description: String? = null,

    @SerializedName("featured_image")
    val featuredImage: String? = null,

    val ingredients: List<String>? = null,

    @SerializedName("long_date_added")
    val longDateAdded: Int? = null,

    @SerializedName("long_date_updated")
    val longDateUpdated: Int? = null,

    val pk: Int? = null,

    val publisher: String? = null,

    val rating: Int? = null,

    @SerializedName("source_url")
    val sourceUrl: String? = null,

    val title: String? = null
)