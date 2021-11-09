package br.com.daniel.ramos.foodrecipe.request;

import br.com.daniel.ramos.foodrecipe.request.responses.RecipeResponse;
import br.com.daniel.ramos.foodrecipe.request.responses.RecipeSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Onde teremos todas as requests da API
 */
public interface RecipeApi {

    // SEARCH
    @GET("api/v2/recipes")
    Call<RecipeSearchResponse> searchRecipe(
            @Query("q") String query,
            @Query("page") String page
    );

    // GET RECIPE
    @GET("api/get")
    Call<RecipeResponse> getRecipe(
            @Query("rId") String recipe_id
    );
}
