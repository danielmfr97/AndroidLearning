package br.com.daniel.ramos.foodrecipe.request.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import br.com.daniel.ramos.foodrecipe.models.Recipe;

public class RecipeResponse {

    @SerializedName("recipe") // Especifica para o retrofit o que buscar na response
    @Expose() // Gson consegue serializar/deserializar o dado que vem da web response
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "recipe=" + recipe +
                '}';
    }
}
