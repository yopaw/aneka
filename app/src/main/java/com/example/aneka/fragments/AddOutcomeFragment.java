package com.example.aneka.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aneka.R;
import com.example.aneka.model.Outcome;
import com.example.aneka.model.Outcome;
import com.example.aneka.repositories.OutcomeRepository;
import com.example.aneka.repositories.OutcomeRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class AddOutcomeFragment extends Fragment implements View.OnClickListener {

    private EditText txtOutcome;
    private Button btnAddOutcome;
    private OutcomeRepository outcomeRepository;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_outcome,container,false);

        outcomeRepository = OutcomeRepository.getInstance();

        txtOutcome = view.findViewById(R.id.txtOutcome);
        btnAddOutcome = view.findViewById(R.id.btnAddOutcome);

        btnAddOutcome.setOnClickListener(this);

        return view;
    }

    private void insertOutcomeData(){

        final String outcomeName = txtOutcome.getText().toString();
        final Calendar calendar = Calendar.getInstance();
        final Date currentDate = calendar.getTime();
        final Outcome newOutcome = new Outcome(outcomeName,0,currentDate);

        outcomeRepository.insertOutcomeData(getContext(),newOutcome);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddOutcome:
                insertOutcomeData();
                break;
        }
    }
}
