package com.example.aneka.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aneka.adapters.IncomeTransactionAdapter;
import com.example.aneka.model.IncomeTransaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.annotation.Nullable;

public class IncomeTransactionRepository {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static Vector<IncomeTransaction> incomeTransactions;
    private static IncomeTransactionRepository instance;
    private static IncomeTransactionAdapter incomeTransactionAdapter;
    private static boolean doGetData = false;

    private IncomeTransactionRepository(){
        incomeTransactions = new Vector<>();
        listenIncomeTransactionCollection();
    }

    public static IncomeTransactionRepository getInstance(){
        if(instance == null) instance = new IncomeTransactionRepository();
        return instance;
    }

    public IncomeTransactionAdapter getIncomeTransactionAdapter(){
        return incomeTransactionAdapter;
    }

    private void listenIncomeTransactionCollection(){
        incomeTransactions.clear();
        db.collection("income_transactions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                final IncomeTransaction currentIncomeTransaction = queryDocumentSnapshot.toObject(IncomeTransaction.class);
                                currentIncomeTransaction.setId(queryDocumentSnapshot.getId());
                                currentIncomeTransaction.setId(queryDocumentSnapshot.getId());
                                if(!incomeTransactions.contains(currentIncomeTransaction)) incomeTransactions.add(currentIncomeTransaction);
                            }
                        }
                    }
                });
        if(!doGetData){
            incomeTransactionAdapter = new IncomeTransactionAdapter(incomeTransactions);
            doGetData = false;
        }
        else incomeTransactionAdapter.notifyDataSetChanged();
    }

    public void insertIncomeTransactionData(final Context context, final IncomeTransaction newIncomeTransaction){
        final String incomeId = IncomeRepository.getIncomeIdByIncomeName(newIncomeTransaction.getIncomeName());
        final Integer incomeOldValue = IncomeRepository.getIncomeValueByIncomeId(incomeId);
        final Integer incomeAdditionalValue = newIncomeTransaction.getIncomeValue();

        db.collection("income_transactions")
                .add(newIncomeTransaction)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(context, "Data Transaksi Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                            IncomeRepository.updatePriceIncomeData(context,incomeId,incomeOldValue,incomeAdditionalValue);
                            IncomeRepository.listenIncomeCollection();
                        }
                    }
                });
        listenIncomeTransactionCollection();
    }

    public void getTodayIncomeTransaction(){
        final Calendar calendar = Calendar.getInstance();
        final long milis = System.currentTimeMillis();
        final Date currentDate = new Date(milis);



    }

    public Vector<IncomeTransaction> getAllIncomeTransacstions(){
        return incomeTransactions;
    }

}
