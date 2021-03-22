package com.example.aneka.repositories;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aneka.adapters.IncomeAdapter;
import com.example.aneka.model.Income;
import com.example.aneka.model.User;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Vector;

import javax.annotation.Nullable;

public class IncomeRepository {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static IncomeRepository instance = null;
    private Vector<Income> incomes = new Vector<>();
    private static IncomeAdapter incomeAdapter;

    private IncomeRepository(){
        loadIncomeData();
        //listenIncomeCollection();
    }

    public static IncomeAdapter getIncomeAdapter(){
        return incomeAdapter;
    }

    public static IncomeRepository getInstance(){
        if(instance == null) instance = new IncomeRepository();
        return  instance;
    }

    public void test(){

    }

    public Vector<Income> getAllIncomes(){
        return incomes;
    }

    private void loadIncomeData(){
        db.collection("incomes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                final Income currentIncome = documentSnapshot.toObject(Income.class);
                                if(!incomes.contains(currentIncome)) incomes.add(currentIncome);
                            }
                            incomeAdapter = new IncomeAdapter(incomes);
                        }
                    }
                });
    }

    private void listenIncomeCollection(){
        db.collection("incomes")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange document : queryDocumentSnapshots.getDocumentChanges()){
                            final Income currentIncome = document.getDocument().toObject(Income.class);
                            if(!incomes.contains(currentIncome)) incomes.add(currentIncome);
                        }
                    }
                });
    }

    public static void insertIncomeData(final Context activity, final Income newIncome){
        db.collection("incomes")
                .add(newIncome)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity, "Sukses Menambahkan Data", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(activity, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
