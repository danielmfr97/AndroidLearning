package br.com.daniel.ramos.learningjetpackcompose.network.model

import br.com.daniel.ramos.learningjetpackcompose.domain.model.Recipe
import br.com.daniel.ramos.learningjetpackcompose.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {
    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            publisher = model.publisher,
            rating = model.rating,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstructions = model.cookingInstructions,
            ingredients = model.ingredients,
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            publisher = domainModel.publisher,
            rating = domainModel.rating,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated
        )
    }

    fun fromDomainList(initial: List<Recipe>) : List<RecipeDto> {
        return initial.map{mapFromDomainModel(it)}
    }

    fun toDomainList(initial: List<RecipeDto>): List<Recipe> {
        return initial.map{mapToDomainModel(it)}
    }
}