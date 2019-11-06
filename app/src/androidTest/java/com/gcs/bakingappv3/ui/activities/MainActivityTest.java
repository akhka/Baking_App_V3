package com.gcs.bakingappv3.ui.activities;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.gcs.bakingappv3.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void pullToRefresh_shouldPass() throws Exception {
        onView(withId(R.id.swipeRefresh_recipe_fragment)).perform(swipeDown());
    }

    @Test
    public void loadMore_shouldPass() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}