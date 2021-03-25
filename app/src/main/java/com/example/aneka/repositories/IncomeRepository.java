package com.example.aneka.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.aneka.adapters.IncomeAdapter;
import com.example.aneka.model.Income;
import com.google.android.gms.tasks.OnCompleteListener;
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

public class IncomeRepository extends ViewModel {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static IncomeRepository instance = null;
    private static Vector<Income> incomes = new Vector<>();
    private static IncomeAdapter incomeAdapter;
    private static boolean doGetData = false;

    private IncomeRepository(){
        listenIncomeCollection();
    }

    public static IncomeAdapter getIncomeAdapter(){
        return incomeAdapter;
    }

    public static IncomeRepository getInstance(){
        if(instance == null) instance = new IncomeRepository();
        return  instance;
    }

    public Vector<Income> getAllIncomes(){
        return incomes;
    }

    public static void listenIncomeCollection(){
        incomes.clear();
        db.collection("incomes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                final Income currentIncome = queryDocumentSnapshot.toObject(Income.class);
                                currentIncome.setId(queryDocumentSnapshot.getId());
                                if(!incomes.contains(currentIncome)) incomes.add(currentIncome);
                            }
                        }
                    }
                });
        if(!doGetData){
            incomeAdapter = new IncomeAdapter(incomes);
            doGetData = true;
        }
        else incomeAdapter.notifyDataSetChanged();
    }

    public static void updatePriceIncomeData(final Context context,final String incomeId, final Integer oldPrice,final Integer additionalPrice){
        db.collection("incomes")
                .document(incomeId)
                .update("incomeValue",oldPrice+additionalPrice)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
        listenIncomeCollection();
    }

    public static String getIncomeIdByIncomeName(final String incomeName){
        for(Income income : incomes){
            if(income.getIncomeName().equals(incomeName)) return income.getId();
        }
        return null;
    }

    public static Integer getIncomeValueByIncomeId(final String incomeId){
        for(Income income : incomes){
            if(income.getId().equals(incomeId)) return income.getIncomeValue();
        }
        return null;
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
        listenIncomeCollection();
    }

}
