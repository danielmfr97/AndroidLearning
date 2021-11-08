package br.com.daniel.ramos.foodrecipe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RecipeListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RecipeListActivity", "Clicou");
                showProgressBar(mProgressBar.getVisibility() != View.VISIBLE);
            }
        });

    }
}
