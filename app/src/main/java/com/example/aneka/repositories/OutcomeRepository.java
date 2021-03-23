package com.example.aneka.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.aneka.adapters.OutcomeAdapter;
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

    private void listenOutcomeCollection(){
        db.collection("outcomes")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange document : queryDocumentSnapshots.getDocumentChanges()){
                            final Outcome currentOutcome = document.getDocument().toObject(Outcome.class);
                            currentOutcome.setId(document.getDocument().getId());
                            if(!outcomes.contains(currentOutcome)) outcomes.add(currentOutcome);
                        }
                        if(!doGetData){
                            outcomeAdapter = new OutcomeAdapter(outcomes);
                            doGetData = true;
                        }
                        else outcomeAdapter.notifyDataSetChanged();
                    }
                });
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
    }
}
