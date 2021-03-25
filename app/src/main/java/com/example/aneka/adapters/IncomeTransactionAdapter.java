package com.example.aneka.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aneka.R;
import com.example.aneka.model.IncomeTransaction;

import java.util.Vector;

public class IncomeTransactionAdapter extends RecyclerView.Adapter<IncomeTransactionAdapter.ViewHolder> {

    private Vector<IncomeTransaction> incomeTransactions;

    public IncomeTransactionAdapter(final Vector<IncomeTransaction> newIncomeTransactions){
        incomeTransactions = newIncomeTransactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewIncomeTransaction = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.income_transaction_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(viewIncomeTransaction);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final IncomeTransaction incomeTransaction = incomeTransactions.get(position);

        final String incomeNameText = "Income Name: "+incomeTransaction.getIncomeName();
        final String incomeValueText = "Income Value: "+incomeTransaction.getIncomeValue();

        holder.tvIncomeTransactionIndex.setText(String.valueOf(position+1));
        holder.tvIncomeTransactionDate.setText(incomeTransaction.getTransactionDate().toString());
        holder.tvIncomeName.setText(incomeNameText);
        holder.tvIncomeTransactionValue.setText(incomeValueText);
        holder.tvIncomeTransactionNote.setText(incomeTransaction.getTransactionNote());
    }

    @Override
    public int getItemCount() {
        return incomeTransactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIncomeTransactionIndex, tvIncomeName, tvIncomeTransactionValue;
        private TextView tvIncomeTransactionDate, tvIncomeTransactionNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIncomeTransactionIndex = itemView.findViewById(R.id.tvIncomeTransactionIndex);
            tvIncomeName  = itemView.findViewById(R.id.tvIncomeName);
            tvIncomeTransactionValue = itemView.findViewById(R.id.tvIncomeTransactionValue);
            tvIncomeTransactionDate = itemView.findViewById(R.id.tvIncomeTransactionDate);
            tvIncomeTransactionNote = itemView.findViewById(R.id.tvIncomeTransactionNote);
        }
    }

}
