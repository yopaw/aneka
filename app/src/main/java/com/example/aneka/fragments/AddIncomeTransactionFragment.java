package com.example.aneka.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aneka.R;
import com.example.aneka.model.Income;
import com.example.aneka.model.IncomeTransaction;
import com.example.aneka.repositories.IncomeRepository;
import com.example.aneka.repositories.IncomeTransactionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class AddIncomeTransactionFragment extends Fragment implements View.OnClickListener, CalendarView.OnDateChangeListener {

    private Spinner spIncomeName;
    private EditText txtIncomeValue,txtTransactionNote;
    private Button btnAddIncomeTransaction;
    private CalendarView cvTransasctionDate;

    private final IncomeRepository incomeRepository = IncomeRepository.getInstance();
    private final IncomeTransactionRepository incomeTransactionRepository =
            IncomeTransactionRepository.getInstance();


    private final Vector<Income> incomes = incomeRepository.getAllIncomes();
    private Vector<String> incomeNames = new Vector<>();
    private ArrayAdapter<String> incomeNameAdapter;

    private String transactionDate = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_income_transaction,container,false);

        loadIncomeData();
        incomeNameAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,incomeNames);
        spIncomeName.setAdapter(incomeNameAdapter);

        spIncomeName = view.findViewById(R.id.spIncomeName);
        txtIncomeValue = view.findViewById(R.id.txtIncomeValue);
        txtTransactionNote = view.findViewById(R.id.txtTransactionNote);
        btnAddIncomeTransaction = view.findViewById(R.id.btnAddTransaction);
        cvTransasctionDate = view.findViewById(R.id.cvTransactionDate);
        cvTransasctionDate.setOnDateChangeListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddTransaction:
                addIncomeTransaction();
                break;
        }
    }

    private void loadIncomeData(){
        for(Income income : incomes){
            incomeNames.add(income.getIncomeName());
        }
    }

    private void addIncomeTransaction(){

        final String incomeName = spIncomeName.getSelectedItem().toString();
        final String transactionNotes = txtTransactionNote.getText().toString();
        final Integer incomeValue = Integer.parseInt(txtIncomeValue.getText().toString());
        final Calendar calendar = Calendar.getInstance();
        final Date currentDate = calendar.getTime();
        final String pattern = "yyyy-MM-dd";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date newTransactionDate = null;

        if(transactionDate.equals("")) newTransactionDate = currentDate;
        else {
            try {
                newTransactionDate = simpleDateFormat.parse(transactionDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        final IncomeTransaction newIncomeTransaction =
                new IncomeTransaction(incomeName,incomeValue,newTransactionDate,transactionNotes);

        incomeTransactionRepository.insertIncomeTransactionData(getContext(), newIncomeTransaction);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        transactionDate = year+"-"+month+"-"+dayOfMonth;
    }
}
