package com.gcs.bakingappv3.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Recipe;
import com.gcs.bakingappv3.ui.fragments.RecipesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;
    private FrameLayout recipes_container;
    private RecipesFragment recipeFragment;


    // This just for test
    /*@Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_0:{
                if (recipes_container.getVisibility() == View.VISIBLE)
                    recipes_container.setVisibility(View.GONE);
                else
                    recipes_container.setVisibility(View.VISIBLE);
            }
            case KeyEvent.KEYCODE_1:{
                fragment.addItem(new Recipe("added"));
                fragment.updateData();
            }
        }
        return super.onKeyUp(keyCode, event);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Baking App");

        recipes_container = findViewById(R.id.recipes_rv_container);
        recipeFragment = new RecipesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.recipes_rv_container, recipeFragment).commit();


    }
}
