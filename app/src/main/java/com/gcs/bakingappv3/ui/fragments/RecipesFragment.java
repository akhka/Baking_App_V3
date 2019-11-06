package com.gcs.bakingappv3.ui.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Parcelable;
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

    private Handler mainHandler = new Handler();

    private RecyclerView recipes_rv;
    private RecipeAdapter adapter;
    private List<Recipe> recipes = new ArrayList<>();
    private Parcelable savedRecyclerLayout;

    private SwipeRefreshLayout swipeRefresh;

    private ApiInterface api;


    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        swipeRefresh = view.findViewById(R.id.swipeRefresh_recipe_fragment);

        if (savedInstanceState != null){
            savedRecyclerLayout = savedInstanceState.getParcelable("recycler_layout");
            recipes = savedInstanceState.getParcelableArrayList("recipe_list");
        }

        recipes_rv = view.findViewById(R.id.recipes_rv);
        adapter = new RecipeAdapter(getContext(), recipes);
        if (getResources().getBoolean(R.bool.isLand)){
            recipes_rv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }
        else{
            recipes_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        recipes_rv.setAdapter(adapter);

        if (recipes.size() == 0 || recipes.isEmpty()){
            if (checkConnection()){
                importData();
            }
            else
                Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }

        if (savedRecyclerLayout != null){
            recipes_rv.getLayoutManager().onRestoreInstanceState(savedRecyclerLayout);
        }

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                importData();
            }
        });

        return view;
    }


    public void updateData(){
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("recipe_list", (ArrayList<? extends Parcelable>) recipes);
        outState.putParcelable("recycler_layout", recipes_rv.getLayoutManager().onSaveInstanceState());
    }


    private void importData(){
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
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkConnection())
            importData();
        else
            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
    }


    private boolean checkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
            return true;
        }
        else {
            return false;
        }
    }
}
