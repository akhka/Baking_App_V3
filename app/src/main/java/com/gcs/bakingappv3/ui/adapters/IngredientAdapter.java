package com.gcs.bakingappv3.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;

    public IngredientAdapter(Context context, List<Ingredient> ingredients){
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.quantity_text.setText(String.valueOf(ingredients.get(position).getQuantity()));
        holder.measure_text.setText(ingredients.get(position).getMeasure());
        holder.ingredient_text.setText(ingredients.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView quantity_text;
        private TextView measure_text;
        private TextView ingredient_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity_text = itemView.findViewById(R.id.display_quantity);
            measure_text = itemView.findViewById(R.id.display_measure);
            ingredient_text = itemView.findViewById(R.id.display_ingredient);
        }
    }
}
