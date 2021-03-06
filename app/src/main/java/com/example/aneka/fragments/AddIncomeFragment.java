package com.example.aneka.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aneka.R;
import com.example.aneka.adapters.IncomeAdapter;
import com.example.aneka.model.Income;
import com.example.aneka.repositories.IncomeRepository;
import com.example.aneka.validations.IncomeValidation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class AddIncomeFragment extends Fragment implements View.OnClickListener {

    private EditText txtIncome;
    private Button btnAddIncome;
    private static IncomeRepository incomeRepository = IncomeRepository.getInstance();
    private final Vector<Income> incomes = incomeRepository.getAllIncomes();
    private final Vector<String> incomeNames = new Vector<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_income,container,false);
        loadIncomeData();


        txtIncome = view.findViewById(R.id.txtIncome);
        btnAddIncome = view.findViewById(R.id.btnAddIncome);

        btnAddIncome.setOnClickListener(this);

        return view;
    }

    private void loadIncomeData(){
        for(Income income : incomes){
            incomeNames.add(income.getIncomeName());
        }
    }

    private void insertIncomeData(){

        final String incomeName = txtIncome.getText().toString();
        final Calendar calendar = Calendar.getInstance();
        final Date currentDate = calendar.getTime();
        final Income newIncome = new Income(incomeName,currentDate,0);

        if(!IncomeValidation.checkIncomeNames(incomeNames,incomeName))
            IncomeRepository.insertIncomeData(getContext(),newIncome);
        else Toast.makeText(getContext(), "Nama Income Telah Terpakai", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddIncome:
                insertIncomeData();
                break;
        }
    }

}
