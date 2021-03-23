package com.example.aneka.repositories;

import androidx.annotation.NonNull;

import com.example.aneka.model.IncomeTransacstion;
import com.example.aneka.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.annotation.Nullable;

public class IncomeTransactionRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Vector<IncomeTransacstion> incomeTransacstions;
    private static IncomeTransactionRepository instance;

    private IncomeTransactionRepository(){
        incomeTransacstions = new Vector<>();
        listenIncomeTransactionCollection();
    }

    public static IncomeTransactionRepository getInstance(){
        if(instance == null) instance = new IncomeTransactionRepository();
        return instance;
    }

    private void loadIncomeTransactionData(){
        db.collection("income_transactions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                            final IncomeTransacstion currentIncomeTransaction = queryDocumentSnapshot.toObject(IncomeTransacstion.class);
                            if(!incomeTransacstions.contains(currentIncomeTransaction))
                                incomeTransacstions.add(currentIncomeTransaction);
                        }
                    }
                });
    }

    private void listenIncomeTransactionCollection(){
        db.collection("income_transactions")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange document : queryDocumentSnapshots.getDocumentChanges()){
                            final IncomeTransacstion currentIncomeTransaction = document.getDocument().toObject(IncomeTransacstion.class);
                            if(!incomeTransacstions.contains(currentIncomeTransaction))
                                incomeTransacstions.add(currentIncomeTransaction);
                        }
                    }
                });
    }

    public void getTodayIncomeTransaction(){
        final Calendar calendar = Calendar.getInstance();
        final long milis = System.currentTimeMillis();
        final Date currentDate = new Date(milis);

    }

}
