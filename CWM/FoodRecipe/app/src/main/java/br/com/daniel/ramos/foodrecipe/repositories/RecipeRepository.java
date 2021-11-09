package br.com.daniel.ramos.foodrecipe.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.daniel.ramos.foodrecipe.models.Recipe;

public class RecipeRepository {
    private static RecipeRepository instance;
    private MutableLiveData<List<Recipe>> mRecipes;

    public static RecipeRepository getInstance() {
        if (instance == null)
            instance = new RecipeRepository();
        return instance;
    }

    private RecipeRepository() {
        mRecipes = new MutableLiveData<>();
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
