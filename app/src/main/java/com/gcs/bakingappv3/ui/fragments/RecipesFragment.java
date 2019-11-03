package com.gcs.bakingappv3.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Recipe;
import com.gcs.bakingappv3.ui.adapters.RecipeAdapter;
import com.gcs.bakingappv3.utils.network.ApiClient;
import com.gcs.bakingappv3.utils.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipesFragment extends Fragment {


    private RecyclerView recipes_rv;
    private RecipeAdapter adapter;
    private List<Recipe> recipes = new ArrayList<>();

    private ApiInterface api;


    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        recipes_rv = view.findViewById(R.id.recipes_rv);
        adapter = new RecipeAdapter(getContext(), recipes);
        recipes_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recipes_rv.setAdapter(adapter);

        api = ApiClient.getApiClient();
        Call<List<Recipe>> call = api.getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error while loading!", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Recipe> recipeList = response.body();
                recipes.clear();
                recipes.addAll(recipeList);
                updateData();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    public void addItem(Recipe recipe){
        recipes.add(recipe);
    }

    public void updateData(){
        adapter.notifyDataSetChanged();
    }

}
