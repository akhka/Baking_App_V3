package com.gcs.bakingappv3.utils.network;

import com.gcs.bakingappv3.data.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();

}
