package com.example.aneka.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aneka.R;
import com.example.aneka.repositories.IncomeTransactionRepository;
import com.example.aneka.repositories.OutcomeRepository;

public class ViewIncomeTransactionFragment extends Fragment {

    private RecyclerView rvIncomeTransaction;
    private RecyclerView.LayoutManager layoutManager;
    private final IncomeTransactionRepository incomeTransactionRepository = IncomeTransactionRepository.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_income_transaction,container,false);

        rvIncomeTransaction = view.findViewById(R.id.rvIncomeTransaction);
        layoutManager = new LinearLayoutManager(getContext());

        rvIncomeTransaction.setLayoutManager(layoutManager);
        rvIncomeTransaction.setAdapter(incomeTransactionRepository.getIncomeTransactionAdapter());

        return  view;
    }
}
