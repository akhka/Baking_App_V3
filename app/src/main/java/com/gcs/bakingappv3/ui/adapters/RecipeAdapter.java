package com.gcs.bakingappv3.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Recipe;
import com.gcs.bakingappv3.ui.activities.DetailsActivity;
import com.gcs.bakingappv3.utils.Constants;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes){
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recipeTitle.setText(recipes.get(position).getName());
        holder.recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                int size = recipes.get(position).getIngredients().size();
                System.out.println(size);
                intent.putExtra(Constants.RECIPE_PARCABLE, recipes.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView recipeCard;
        private TextView recipeTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeCard = itemView.findViewById(R.id.recipe_card);
            recipeTitle = itemView.findViewById(R.id.recipe_card_name);
        }
    }
}
