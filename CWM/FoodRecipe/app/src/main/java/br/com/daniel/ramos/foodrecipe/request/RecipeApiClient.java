package br.com.daniel.ramos.foodrecipe.request;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import br.com.daniel.ramos.foodrecipe.AppExecutors;
import br.com.daniel.ramos.foodrecipe.models.Recipe;
import br.com.daniel.ramos.foodrecipe.util.Constants;

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

    public void searchRecipesApi() {
        final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                // retrieve data from rest api
//                mRecipes.postValue();
            }
        });

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Interrupt the background thread request when timed out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
