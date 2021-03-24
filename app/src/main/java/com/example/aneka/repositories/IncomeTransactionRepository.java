package com.example.aneka.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aneka.model.IncomeTransaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.annotation.Nullable;

public class IncomeTransactionRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Vector<IncomeTransaction> incomeTransactions;
    private static IncomeTransactionRepository instance;

    private IncomeTransactionRepository(){
        incomeTransactions = new Vector<>();
        listenIncomeTransactionCollection();
    }

    public static IncomeTransactionRepository getInstance(){
        if(instance == null) instance = new IncomeTransactionRepository();
        return instance;
    }

    private void listenIncomeTransactionCollection(){
        db.collection("income_transactions")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange document : queryDocumentSnapshots.getDocumentChanges()){
                            final IncomeTransaction currentIncomeTransaction = document.getDocument().toObject(IncomeTransaction.class);
                            if(!incomeTransactions.contains(currentIncomeTransaction))
                                incomeTransactions.add(currentIncomeTransaction);
                        }
                    }
                });
    }

    public void insertIncomeTransactionData(final Context context, final IncomeTransaction newIncomeTransaction){
        db.collection("income_transactions")
                .add(newIncomeTransaction)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(context, "Data Transaksi Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
