package com.example.aneka.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aneka.R;
import com.example.aneka.model.Outcome;

import java.util.Vector;

public class OutcomeAdapter extends RecyclerView.Adapter<OutcomeAdapter.ViewHolder> {

    private Vector<Outcome> Outcomes;

    public OutcomeAdapter(Vector<Outcome> OutcomeList){
        Outcomes = OutcomeList;
    }

    public Vector<Outcome> getAllOutcomeFromAdapter(){
        return Outcomes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewOutcome = LayoutInflater.from(parent.getContext()).inflate(R.layout.outcome_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(viewOutcome);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Outcome Outcome = Outcomes.get(position);

        holder.tvOutcomeIndex.setText(String.valueOf(position+1));
        holder.tvOutcomeName.setText(Outcome.getOutcomeName());
        holder.tvOutcomeValue.setText(Outcome.getOutcomeValue().toString());
    }

    @Override
    public int getItemCount() {
        return Outcomes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOutcomeIndex,tvOutcomeName,tvOutcomeValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOutcomeIndex = itemView.findViewById(R.id.tvOutcomeIndex);
            tvOutcomeName  = itemView.findViewById(R.id.tvOutcomeName);
            tvOutcomeValue = itemView.findViewById(R.id.tvOutcomeValue);
        }
    }
}
