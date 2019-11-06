package com.gcs.bakingappv3.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Step;
import com.gcs.bakingappv3.ui.fragments.ExoPlayerFragment;
import com.gcs.bakingappv3.ui.fragments.NoVideoFragment;
import com.gcs.bakingappv3.ui.fragments.StepDetailsFragment;
import com.gcs.bakingappv3.utils.Constants;

public class StepDetailsActivity extends AppCompatActivity {

    private Toolbar stepDetailsToolbar;
    private Step step;

    private FrameLayout videoPlayer_container;
    private FrameLayout stepDetails_container;
    private FrameLayout stepNav_container;

    private ExoPlayerFragment exoFragment;
    private NoVideoFragment novideoFragment;
    private StepDetailsFragment stepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        Intent intent = getIntent();
        step = intent.getParcelableExtra(Constants.STEP_PARCABLE);
        int stepNumber = intent.getIntExtra("position", 0);

        String title;
        if (stepNumber == 0){
            title = "Step - " + step.getShortDescription();
        }
        else {
            title = "Step " + stepNumber + " - " + step.getShortDescription();
        }

        stepDetailsToolbar = findViewById(R.id.stepDetail_toolbar);
        setSupportActionBar(stepDetailsToolbar);
        getSupportActionBar().setTitle(title);

        videoPlayer_container = findViewById(R.id.videoPlayer_container);
        stepDetails_container = findViewById(R.id.step_details_container);
        stepNav_container = findViewById(R.id.step_nav_container);

        if (step.getVideoURL().equals("")){
            novideoFragment = new NoVideoFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.videoPlayer_container, novideoFragment).commit();
        }
        else{
            exoFragment = new ExoPlayerFragment();
            exoFragment.setVideoUrl(step.getVideoURL());
            getSupportFragmentManager().beginTransaction().add(R.id.videoPlayer_container, exoFragment).commit();
        }

        stepFragment = new StepDetailsFragment();
        stepFragment.setFullDescription(step.getDescription());
        getSupportFragmentManager().beginTransaction().add(R.id.step_details_container, stepFragment).commit();
    }
}
