package br.com.daniel.ramos.foodrecipe.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.daniel.ramos.foodrecipe.models.Recipe;

/**
 * Responsable for Getting, holding, retrieving the recipes that we'll display on app
 */
public class RecipeListViewModel extends ViewModel {

    //
    private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

    public RecipeListViewModel() {

    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }


}
