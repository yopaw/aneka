package com.example.aneka.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.aneka.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IncomeFragment extends Fragment {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income,container,false);

        toolbar = view.findViewById(R.id.toolbarIncome);
        bottomNavigationView = view.findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.income_fragment_container, new AddIncomeFragment()).commit();
        }
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_view:
                    selectedFragment = new ViewIncomeFragment();
                    break;
                case R.id.nav_add:
                    selectedFragment = new IncomeFragment();
                    break;
            }
            getFragmentManager().beginTransaction().replace(R.id.income_fragment_container,selectedFragment).commit();

            return true;
        }
    };

}
