package com.example.aneka.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aneka.R;
import com.example.aneka.fragments.HomeFragment;
import com.example.aneka.fragments.IncomeFragment;
import com.example.aneka.fragments.IncomeTransactionFragment;
import com.example.aneka.fragments.OutcomeFragment;
import com.example.aneka.fragments.OutcomeTransactionFragment;
import com.example.aneka.repositories.IncomeRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private TextView lblUsername;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    private IncomeRepository incomeRepository = IncomeRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUserData();

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar
        ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView = findViewById(R.id.nav_bottom);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    private void setUserData(){
        navigationView = findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        lblUsername = headerLayout.findViewById(R.id.lblUsername);

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        lblUsername.setText(sharedPreferences.getString("username",""));
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.nav_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.nav_income:
                selectedFragment = new IncomeFragment();
                break;
            case R.id.nav_outcome:
                selectedFragment = new OutcomeFragment();
                break;
            case R.id.nav_income_transaction:
                selectedFragment = new IncomeTransactionFragment();
                break;
            case R.id.nav_outcome_transaction:
                selectedFragment = new OutcomeTransactionFragment();
                break;

        }
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    }

}