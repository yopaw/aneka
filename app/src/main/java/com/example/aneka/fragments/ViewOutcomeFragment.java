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
import com.example.aneka.repositories.OutcomeRepository;

public class ViewOutcomeFragment extends Fragment {

    private RecyclerView rvOutcome;
    private RecyclerView.LayoutManager layoutManager;
    private OutcomeRepository outcomeRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_outcome,container,false);

        rvOutcome = view.findViewById(R.id.rvOutcome);
        layoutManager = new LinearLayoutManager(getContext());
        outcomeRepository = OutcomeRepository.getInstance();

        rvOutcome.setLayoutManager(layoutManager);
        rvOutcome.setAdapter(OutcomeRepository.getOutcomeAdapter());

        return view;
    }
}
