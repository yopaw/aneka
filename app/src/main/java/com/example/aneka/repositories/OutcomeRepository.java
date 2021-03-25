package com.example.aneka.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aneka.adapters.OutcomeAdapter;
import com.example.aneka.adapters.OutcomeAdapter;
import com.example.aneka.model.Outcome;
import com.example.aneka.model.Outcome;
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


public class OutcomeRepository {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static OutcomeRepository instance = null;
    private static Vector<Outcome> outcomes = new Vector<>();
    private static OutcomeAdapter outcomeAdapter;
    private static boolean doGetData = false;

    private OutcomeRepository(){
        listenOutcomeCollection();
    }

    public static OutcomeAdapter getOutcomeAdapter(){
        return outcomeAdapter;
    }

    public static OutcomeRepository getInstance(){
        if(instance == null) instance = new OutcomeRepository();
        return  instance;
    }

    public Vector<Outcome> getAllOutcomes(){
        return outcomes;
    }

    public static void listenOutcomeCollection(){
        outcomes.clear();
        db.collection("outcomes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                final Outcome currentOutcome = queryDocumentSnapshot.toObject(Outcome.class);
                                currentOutcome.setId(queryDocumentSnapshot.getId());
                                if(!outcomes.contains(currentOutcome)) outcomes.add(currentOutcome);
                            }
                        }
                    }
                });
        if(!doGetData){
            outcomeAdapter = new OutcomeAdapter(outcomes);
            doGetData = true;
        }
        else outcomeAdapter.notifyDataSetChanged();
    }

    public static void updatePriceOutcomeData(final Context context,final String outcomeId, final Integer oldPrice,final Integer additionalPrice){
        db.collection("outcomes")
                .document(outcomeId)
                .update("outcomeValue",oldPrice+additionalPrice)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
        listenOutcomeCollection();
    }

    public static String getOutcomeIdByOutcomeName(final String outcomeName){
        for(Outcome outcome : outcomes){
            if(outcome.getOutcomeName().equals(outcomeName)) return outcome.getId();
        }
        return null;
    }

    public static Integer getOutcomeValueByOutcomeId(final String outcomeId){
        for(Outcome outcome : outcomes){
            if(outcome.getId().equals(outcomeId)) return outcome.getOutcomeValue();
        }
        return null;
    }

    public static void insertOutcomeData(final Context activity, final Outcome newOutcome){
        db.collection("outcomes")
                .add(newOutcome)
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
        listenOutcomeCollection();
    }
}
