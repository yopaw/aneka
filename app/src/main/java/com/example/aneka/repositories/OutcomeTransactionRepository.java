package com.example.aneka.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aneka.adapters.OutcomeTransactionAdapter;
import com.example.aneka.model.OutcomeTransaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class OutcomeTransactionRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static Vector<OutcomeTransaction> outcomeTransactions;
    private static OutcomeTransactionRepository instance;
    private static OutcomeTransactionAdapter outcomeTransactionAdapter;
    private static boolean doGetData = false;

    private OutcomeTransactionRepository(){
        outcomeTransactions = new Vector<>();
        listenOutcomeTransactionCollection();
    }

    public static OutcomeTransactionRepository getInstance(){
        if(instance == null) instance = new OutcomeTransactionRepository();
        return instance;
    }

    public OutcomeTransactionAdapter getOutcomeTransactionAdapter(){
        return outcomeTransactionAdapter;
    }

    private void listenOutcomeTransactionCollection(){
        outcomeTransactions.clear();
        db.collection("outcome_transactions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                final OutcomeTransaction currentOutcomeTransaction = queryDocumentSnapshot.toObject(OutcomeTransaction.class);
                                currentOutcomeTransaction.setId(queryDocumentSnapshot.getId());
                                if(!outcomeTransactions.contains(currentOutcomeTransaction)) outcomeTransactions.add(currentOutcomeTransaction);
                            }
                        }
                    }
                });
        if(!doGetData){
            outcomeTransactionAdapter = new OutcomeTransactionAdapter(outcomeTransactions);
            doGetData = false;
        }
        else outcomeTransactionAdapter.notifyDataSetChanged();
    }

    public void insertOutcomeTransactionData(final Context context, final OutcomeTransaction newOutcomeTransaction){
        final String outcomeId = OutcomeRepository.getOutcomeIdByOutcomeName(newOutcomeTransaction.getOutcomeName());
        final Integer outcomeOldValue = OutcomeRepository.getOutcomeValueByOutcomeId(outcomeId);
        final Integer outcomeAdditionalValue = newOutcomeTransaction.getOutcomeValue();

        db.collection("outcome_transactions")
                .add(newOutcomeTransaction)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(context, "Data Transaksi Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                            OutcomeRepository.updatePriceOutcomeData(context,outcomeId,outcomeOldValue,outcomeAdditionalValue);
                        }
                    }
                });
        listenOutcomeTransactionCollection();
    }

    public void getTodayOutcomeTransaction(){
        final Calendar calendar = Calendar.getInstance();
        final long milis = System.currentTimeMillis();
        final Date currentDate = new Date(milis);



    }

    public Vector<OutcomeTransaction> getAllOutcomeTransacstions(){
        return outcomeTransactions;
    }
}
