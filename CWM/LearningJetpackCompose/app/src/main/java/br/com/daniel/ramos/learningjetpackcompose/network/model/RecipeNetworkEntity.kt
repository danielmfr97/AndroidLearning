package br.com.daniel.ramos.learningjetpackcompose.network.model

import com.google.gson.annotations.SerializedName

data class RecipeNetworkEntity(
    @SerializedName("cooking_instructions")
    val cookingInstructions: String? = null,

    @SerializedName("cooking_instructions")
    val dateAdded: String? = null,

    @SerializedName("cooking_instructions")
    val dateUpdated: String? = null,

    val description: String? = null,

    val featuredImage: String? = null,

    val ingredients: List<String>? = null,

    @SerializedName("cooking_instructions")
    val longDateAdded: Int? = null,

    @SerializedName("cooking_instructions")
    val longDateUpdated: Int? = null,

    val pk: Int? = null,

    val publisher: String? = null,

    val rating: Int? = null,

    @SerializedName("source_url")
    val sourceUrl: String? = null,

    val title: String? = null
)