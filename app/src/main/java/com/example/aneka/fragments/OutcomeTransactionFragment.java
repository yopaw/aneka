package com.example.aneka.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aneka.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OutcomeTransactionFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outcome_transaction,container,false);

        bottomNavigationView = view.findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.outcome_transaction_fragment_container,
                    new AddOutcomeTransactionFragment()).commit();
        }

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment selectedFragment = null;

        switch(item.getItemId()){
            case R.id.nav_add_outcome_transaction:
                selectedFragment = new AddOutcomeTransactionFragment();
                break;
            case R.id.nav_view_outcome_transaction:
                selectedFragment = new ViewOutcomeTransactionFragment();
                break;
        }

        getFragmentManager().beginTransaction().replace(R.id.outcome_transaction_fragment_container,
                selectedFragment).commit();

        return true;
    }
}
