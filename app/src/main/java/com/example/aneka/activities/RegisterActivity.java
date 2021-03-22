package com.example.aneka.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aneka.R;
import com.example.aneka.model.User;
import com.example.aneka.repositories.UserRepository;
import com.example.aneka.utilities.SecurityUtils;
import com.example.aneka.validations.AuthValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterActivity extends AppCompatActivity {

    private String username;
    private String password;
    private String confirmPassword;
    private String secretCode;

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private EditText txtSecretCode;

    private Button btnRegister;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String secretCodeDB = "";
    private boolean isRegistrationSuccess = false;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSecretCode();
        userRepository = UserRepository.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtSecretCode = findViewById(R.id.txtSecretCode);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                confirmPassword = txtConfirmPassword.getText().toString();
                secretCode = txtSecretCode.getText().toString();

                Toast.makeText(RegisterActivity.this, ""+
                        doRegister(username,password,confirmPassword,secretCode), Toast.LENGTH_SHORT).show();
                if(isRegistrationSuccess) finish();
            }
        });
    }

    private String doRegister(String username, String password, String confirmPassword, String code){
        String message = "";
        if(AuthValidation.checkUsernameAndPasswordIsEmpty(username,password)){
            message = "Username atau Password anda tidak boleh kosong!";
        }
        else if(!AuthValidation.checkPasswordMatchWithConfirmPassword(password,confirmPassword)){
            message = "Konfirmasi password tidak sama dengan Password!";
        }
        else if(!code.equals(secretCodeDB)){
            message =  "Kode rahasia salah, mohon hubungi Admin untuk megetahui kode tersebut!";
        }
        else if(userRepository.getUserByUsername(username) != null){
            message = "Username anda telah terpakai, silahkan ganti username anda!";
        }
        else{
            message = "Anda berhasil teregistrasi sebagai user";
            isRegistrationSuccess = true;
            try {
                final User newUser = new User(username, SecurityUtils.generateStrongPasswordHash(password),
                        SecurityUtils.toHex(SecurityUtils.salt));
                userRepository.insertUserData(newUser);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    private void getSecretCode(){
        db.collection("secrets").limit(1)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            secretCodeDB = document.get("code").toString();
                        }
                    }
            }
            });
    }

}