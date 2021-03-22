package com.example.aneka.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aneka.R;
import com.example.aneka.model.User;
import com.example.aneka.repositories.UserRepository;
import com.example.aneka.validations.AuthValidation;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private String username,password;
    private EditText txtUsername, txtPassword;
    private boolean isLoginSuccess = false;

    private TextView lblCreateAccount;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(checkUserState()) switchActivity(HomeActivity.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userRepository = UserRepository.getInstance();

        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);

        lblCreateAccount = findViewById(R.id.lblCreateAccount);
        lblCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               switchActivity(RegisterActivity.class);
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                try {
                    Toast.makeText(LoginActivity.this, ""+doLogin(username,password), Toast.LENGTH_SHORT).show();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                if(isLoginSuccess){
                    saveUserDataOnSharedPreference();
                    switchActivity(HomeActivity.class);
                }
            }
        });

    }

    private boolean checkUserState(){
        final SharedPreferences sharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE);
        if(sharedPreferences.getString("username","").equals("")) return false;
        return true;
    }

    private void switchActivity(final Class destinationClass){
        Intent intent = new Intent(this, destinationClass);
        startActivity(intent);
    }

    private String doLogin(final String username, final String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if(AuthValidation.checkUsernameAndPasswordIsEmpty(username,password)) return "Username atau Password Tidak boleh Kosong";
        final User userLogin = userRepository.getUserByUsernameAndPassword(username,password);
        if(userLogin == null) return "Username atau Password salah!";
        isLoginSuccess = true;
        return "Sukses";
    }

    private void saveUserDataOnSharedPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.commit();
    }

}