package com.example.aneka.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aneka.R;
import com.example.aneka.model.Income;

import java.util.Vector;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {

    private Vector<Income> incomes;

    public IncomeAdapter(Vector<Income> incomeList){
        incomes = incomeList;
    }

    public Vector<Income> getAllIncomeFromAdapter(){
        return incomes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewIncome = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(viewIncome);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Income income = incomes.get(position);

        holder.tvIncomeIndex.setText(String.valueOf(position));
        holder.tvIncomeName.setText(income.getIncomeName());
        holder.tvIncomeValue.setText(income.getIncomeValue().toString());
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIncomeIndex,tvIncomeName,tvIncomeValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIncomeIndex = itemView.findViewById(R.id.tvIncomeIndex);
            tvIncomeName  = itemView.findViewById(R.id.tvIncomeName);
            tvIncomeValue = itemView.findViewById(R.id.tvIncomeValue);
        }
    }
}
