package com.gcs.bakingappv3.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Ingredient;
import com.gcs.bakingappv3.data.models.Recipe;
import com.gcs.bakingappv3.data.models.Step;
import com.gcs.bakingappv3.ui.fragments.IngredietFragment;
import com.gcs.bakingappv3.ui.fragments.StepListFragment;
import com.gcs.bakingappv3.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar detailsToolbar;
    private FrameLayout ingredient_container;
    private FrameLayout step_list_container;
    private Recipe recipe;
    private IngredietFragment ingredietFragment;
    private StepListFragment stepListFragment;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<Step> steps = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        recipe = intent.getParcelableExtra(Constants.RECIPE_PARCABLE);

        detailsToolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(detailsToolbar);
        getSupportActionBar().setTitle(recipe.getName());
        int id = recipe.getId();
        System.out.println(id);
        int size = recipe.getIngredients().size();
        System.out.println(size);

        ingredient_container = findViewById(R.id.ingredient_container);
        step_list_container = findViewById(R.id.steps_list_container);

        ingredietFragment = new IngredietFragment();
        stepListFragment = new StepListFragment();

        ingredients = recipe.getIngredients();
        ingredietFragment.setIngredients(ingredients);

        steps = recipe.getSteps();
        stepListFragment.setStepList(steps);

        getSupportFragmentManager().beginTransaction().add(R.id.ingredient_container, ingredietFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.steps_list_container, stepListFragment).commit();
    }
}
