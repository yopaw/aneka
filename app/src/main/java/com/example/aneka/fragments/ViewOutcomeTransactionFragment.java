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
import com.example.aneka.repositories.OutcomeTransactionRepository;
import com.example.aneka.repositories.OutcomeRepository;

public class ViewOutcomeTransactionFragment extends Fragment {

    private RecyclerView rvOutcomeTransaction;
    private RecyclerView.LayoutManager layoutManager;
    private final OutcomeTransactionRepository outcomeTransactionRepository = OutcomeTransactionRepository.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_outcome_transaction,container,false);

        rvOutcomeTransaction = view.findViewById(R.id.rvOutcomeTransaction);
        layoutManager = new LinearLayoutManager(getContext());

        rvOutcomeTransaction.setLayoutManager(layoutManager);
        rvOutcomeTransaction.setAdapter(outcomeTransactionRepository.getOutcomeTransactionAdapter());

        return  view;
    }
}
