package br.com.daniel.ramos.learningjetpackcompose.network.response

import br.com.daniel.ramos.learningjetpackcompose.network.model.RecipeNetworkEntity
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse (
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var recipes: List<RecipeNetworkEntity>,


)