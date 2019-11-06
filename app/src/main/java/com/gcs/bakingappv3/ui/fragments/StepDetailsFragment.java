package com.gcs.bakingappv3.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcs.bakingappv3.R;


public class StepDetailsFragment extends Fragment {

    private TextView fullDescription;
    private String fullDescriptionText;

    public StepDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        fullDescription = view.findViewById(R.id.step_full_description_text);

        fullDescription.setText(fullDescriptionText);

        return view;
    }

    public void setFullDescription(String text){
        fullDescriptionText = text;
    }

}
