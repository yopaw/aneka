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

public class OutcomeFragment extends Fragment {

    private BottomNavigationView bottomNavigationView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outcome,container,false);

        bottomNavigationView = view.findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.outcome_fragment_container, new AddOutcomeFragment()).commit();
        }
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_add_outcome:
                            selectedFragment = new AddOutcomeFragment();
                            break;
                        case R.id.nav_view_outcome:
                            selectedFragment = new ViewOutcomeFragment();
                            break;
                    }
                    getFragmentManager().beginTransaction().replace(R.id.outcome_fragment_container,selectedFragment).commit();

                    return true;
                }
            };

}
