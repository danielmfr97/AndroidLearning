package br.com.daniel.ramos.foodrecipe.request;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.daniel.ramos.foodrecipe.models.Recipe;

/**
 * Remote Data Source that will use retrofit
 */
public class RecipeApiClient {

    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> mRecipes;


    public static RecipeApiClient getInstance() {
        if (instance == null)
            instance = new RecipeApiClient();
        return instance;
    }

    public RecipeApiClient() {
        mRecipes = new MutableLiveData<>();
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
