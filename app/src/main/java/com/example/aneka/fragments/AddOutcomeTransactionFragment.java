package com.example.aneka.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aneka.R;
import com.example.aneka.model.Outcome;
import com.example.aneka.model.OutcomeTransaction;
import com.example.aneka.repositories.OutcomeRepository;
import com.example.aneka.repositories.OutcomeTransactionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class AddOutcomeTransactionFragment extends Fragment implements View.OnClickListener, CalendarView.OnDateChangeListener {

    private Spinner spOutcomeName;
    private EditText txtOutcomeValue,txtTransactionNote;
    private Button btnAddOutcomeTransaction;
    private CalendarView cvTransasctionDate;

    private static final OutcomeRepository outcomeRepository = OutcomeRepository.getInstance();
    private final OutcomeTransactionRepository outcomeTransactionRepository =
            OutcomeTransactionRepository.getInstance();

    private Vector<Outcome> outcomes = outcomeRepository.getAllOutcomes();
    private Vector<String> outcomeNames = new Vector<>();
    private ArrayAdapter<String> outcomeNameAdapter;

    private String transactionDate = "";
    private Date newTransactionDate = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_outcome_transaction,container,false);

        loadOutcomeData();

        outcomeNameAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,outcomeNames);

        spOutcomeName = view.findViewById(R.id.spOutcomeName);
        spOutcomeName.setAdapter(outcomeNameAdapter);

        txtOutcomeValue = view.findViewById(R.id.txtOutcomeValue);
        txtTransactionNote = view.findViewById(R.id.txtTransactionNote);

        btnAddOutcomeTransaction = view.findViewById(R.id.btnAddTransaction);
        btnAddOutcomeTransaction.setOnClickListener(this);

        cvTransasctionDate = view.findViewById(R.id.cvTransactionDate);
        cvTransasctionDate.setOnDateChangeListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddTransaction:
                addOutcomeTransaction();
                break;
        }
    }

    private void loadOutcomeData(){
        for(Outcome outcome : outcomes){
            outcomeNames.add(outcome.getOutcomeName());
        }
    }

    private void addOutcomeTransaction(){

        final String outcomeName = spOutcomeName.getSelectedItem().toString();
        final String transactionNotes = txtTransactionNote.getText().toString();
        final Integer outcomeValue = Integer.parseInt(txtOutcomeValue.getText().toString());
        final Calendar calendar = Calendar.getInstance();
        final Date currentDate = calendar.getTime();
        final String pattern = "yyyy-MM-dd";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        if(transactionDate.equals("")) newTransactionDate = currentDate;
        else {
            try {
                newTransactionDate = simpleDateFormat.parse(transactionDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        final OutcomeTransaction newOutcomeTransaction =
                new OutcomeTransaction(outcomeName,outcomeValue,newTransactionDate,transactionNotes);

        outcomeTransactionRepository.insertOutcomeTransactionData(getContext(), newOutcomeTransaction);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        transactionDate = year+"-"+month+"-"+dayOfMonth;
    }
}
