package br.com.daniel.ramos.foodrecipe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.daniel.ramos.foodrecipe.models.Recipe;
import br.com.daniel.ramos.foodrecipe.request.RecipeApi;
import br.com.daniel.ramos.foodrecipe.request.ServiceGenerator;
import br.com.daniel.ramos.foodrecipe.request.responses.RecipeResponse;
import br.com.daniel.ramos.foodrecipe.request.responses.RecipeSearchResponse;
import br.com.daniel.ramos.foodrecipe.util.Testing;
import br.com.daniel.ramos.foodrecipe.viewmodels.RecipeListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {
    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeListViewModel = new ViewModelProvider(this).get(RecipeListViewModel.class);

        subscribeObservers();

        findViewById(R.id.test).setOnClickListener(v -> {
            testRetrofitRequest();
        });
    }

    private void subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, recipes -> {
            if (recipes != null)
                Testing.printListInFor(recipes, TAG);
        });
    }

    private void searchRecipesApi(String query, int pageNumber) {
        mRecipeListViewModel.searchRecipesApi(query, pageNumber);
    }

    private void testRetrofitRequest() {
        searchRecipesApi("chicken breast", 1);
    }
}
