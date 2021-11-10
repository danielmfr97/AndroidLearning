package br.com.daniel.ramos.foodrecipe.util;

import android.util.Log;

import java.util.List;

import br.com.daniel.ramos.foodrecipe.models.Recipe;

public class Testing {

    public static void printListInFor(List<Recipe> list, String tag) {
        for (Recipe item : list) {
            Log.d(tag, "onChanged: " + item.getTitle());
        }
    }
}
