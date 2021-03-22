package com.example.aneka.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aneka.R;
import com.example.aneka.adapters.IncomeAdapter;
import com.example.aneka.model.Income;
import com.example.aneka.repositories.IncomeRepository;
import com.example.aneka.repositories.UserRepository;

public class ViewIncomeFragment extends Fragment {

    private RecyclerView rvIncome;
    private RecyclerView.LayoutManager layoutManager;
    private IncomeRepository incomeRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_income,container,false);

        rvIncome = view.findViewById(R.id.rvIncome);
        layoutManager = new LinearLayoutManager(getContext());
        incomeRepository = IncomeRepository.getInstance();

        rvIncome.setLayoutManager(layoutManager);
        rvIncome.setAdapter(IncomeRepository.getIncomeAdapter());
        Toast.makeText(getContext(), ""+incomeRepository.getAllIncomes().size(), Toast.LENGTH_SHORT).show();

        return  view;
    }
}
