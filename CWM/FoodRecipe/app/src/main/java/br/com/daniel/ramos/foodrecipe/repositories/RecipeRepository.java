package br.com.daniel.ramos.foodrecipe.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.daniel.ramos.foodrecipe.models.Recipe;
import br.com.daniel.ramos.foodrecipe.request.RecipeApiClient;

public class RecipeRepository {
    private static RecipeRepository instance;
    private RecipeApiClient mRecipeApiClient;

    public static RecipeRepository getInstance() {
        if (instance == null)
            instance = new RecipeRepository();
        return instance;
    }

    private RecipeRepository() {
        mRecipeApiClient = new RecipeApiClient();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeApiClient.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (pageNumber == 0)
            pageNumber = 1; // Fallback for error searching for page 0
        mRecipeApiClient.searchRecipesApi(query, pageNumber);
    }
}