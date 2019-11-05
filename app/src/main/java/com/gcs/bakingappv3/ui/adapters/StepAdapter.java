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
import com.gcs.bakingappv3.data.models.Step;
import com.gcs.bakingappv3.ui.activities.StepDetailsActivity;
import com.gcs.bakingappv3.utils.Constants;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private Context context;
    private List<Step> steps;

    public StepAdapter(Context context, List<Step> steps){
        this.context = context;
        this.steps = steps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.step_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.shortDescription.setText("Step " + (position+1) + ":  " + steps.get(position).getShortDescription());
        holder.stepCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StepDetailsActivity.class);
                intent.putExtra(Constants.STEP_PARCABLE, steps.get(position));
                int stepNumber = position+1;
                intent.putExtra("position", stepNumber);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView shortDescription;
        private CardView stepCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shortDescription = itemView.findViewById(R.id.step_short_description);
            stepCard = itemView.findViewById(R.id.step_item_card);
        }
    }
}
