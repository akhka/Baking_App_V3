package com.gcs.bakingappv3.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Ingredient;
import com.gcs.bakingappv3.data.models.Recipe;
import com.gcs.bakingappv3.ui.adapters.IngredientAdapter;

import java.util.ArrayList;
import java.util.List;

public class IngredietFragment extends Fragment {

    private RecyclerView ingredient_rv;
    private IngredientAdapter ingredientAdapter;
    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredietFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingrediet, container, false);
        ingredient_rv = view.findViewById(R.id.ingredient_rv);
        ingredientAdapter = new IngredientAdapter(getContext(), ingredients);
        if (getContext().getResources().getBoolean(R.bool.isLand) == true){
            ingredient_rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }
        else {
            ingredient_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
        ingredient_rv.setAdapter(ingredientAdapter);
        updateData();
        return view;
    }

    public void setIngredients(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    private void updateData(){
        ingredientAdapter.notifyDataSetChanged();
    }

}
