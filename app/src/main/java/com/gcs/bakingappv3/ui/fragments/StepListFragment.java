package com.gcs.bakingappv3.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcs.bakingappv3.R;
import com.gcs.bakingappv3.data.models.Step;
import com.gcs.bakingappv3.ui.adapters.StepAdapter;

import java.util.ArrayList;
import java.util.List;

public class StepListFragment extends Fragment {

    private List<Step> steps = new ArrayList<>();
    private RecyclerView steps_rv;
    private StepAdapter stepAdapter;

    public StepListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_list, container, false);
        steps_rv = view.findViewById(R.id.steps_rv);
        stepAdapter = new StepAdapter(getContext(), steps);
        steps_rv.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        steps_rv.setAdapter(stepAdapter);
        updateData();
        return view;
    }


    public void setStepList(List<Step> steps){
        this.steps = steps;
    }

    private void updateData(){
        stepAdapter.notifyDataSetChanged();
    }

}
