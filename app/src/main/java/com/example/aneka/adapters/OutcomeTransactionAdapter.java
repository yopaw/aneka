package com.example.aneka.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aneka.R;
import com.example.aneka.model.OutcomeTransaction;

import java.util.Vector;

public class OutcomeTransactionAdapter extends RecyclerView.Adapter<OutcomeTransactionAdapter.ViewHolder> {

    private Vector<OutcomeTransaction> outcomeTransactions;

    public OutcomeTransactionAdapter(final Vector<OutcomeTransaction> newOutcomeTransactions){
        outcomeTransactions = newOutcomeTransactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewOutcomeTransaction = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.outcome_transaction_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(viewOutcomeTransaction);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OutcomeTransaction outcomeTransaction = outcomeTransactions.get(position);

        final String outcomeNameText = "Outcome Name: "+outcomeTransaction.getOutcomeName();
        final String outcomeValueText = "Outcome Value: "+outcomeTransaction.getOutcomeValue();
        final String outcomeTransactionDateText = ""+outcomeTransaction.getTransactionDate();

        holder.tvOutcomeTransactionIndex.setText(String.valueOf(position+1));
        holder.tvOutcomeTransactionDate.setText(outcomeTransactionDateText);

        holder.tvOutcomeName.setText(outcomeNameText);
        holder.tvOutcomeTransactionValue.setText(outcomeValueText);
        holder.tvOutcomeTransactionNote.setText(outcomeTransaction.getTransactionNote());
    }

    @Override
    public int getItemCount() {
        return outcomeTransactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOutcomeTransactionIndex, tvOutcomeName, tvOutcomeTransactionValue;
        private TextView tvOutcomeTransactionDate, tvOutcomeTransactionNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOutcomeTransactionIndex = itemView.findViewById(R.id.tvOutcomeTransactionIndex);
            tvOutcomeName  = itemView.findViewById(R.id.tvOutcomeName);
            tvOutcomeTransactionValue = itemView.findViewById(R.id.tvOutcomeTransactionValue);
            tvOutcomeTransactionDate = itemView.findViewById(R.id.tvOutcomeTransactionDate);
            tvOutcomeTransactionNote = itemView.findViewById(R.id.tvOutcomeTransactionNote);
        }
    }
}
