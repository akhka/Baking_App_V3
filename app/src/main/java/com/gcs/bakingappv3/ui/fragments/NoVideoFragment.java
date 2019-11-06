package com.gcs.bakingappv3.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcs.bakingappv3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoVideoFragment extends Fragment {


    public NoVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_video, container, false);
    }

}
