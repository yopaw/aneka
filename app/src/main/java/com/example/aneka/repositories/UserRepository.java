package com.example.aneka.repositories;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.aneka.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Vector;

import javax.annotation.Nullable;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class UserRepository {

    private  static UserRepository instance = null;
    private Vector<User> users;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private UserRepository(){
        users = new Vector<>();
        loadUserData();
        listenUserCollection();
    }

    public static UserRepository getInstance(){
        if(instance == null) instance = new UserRepository();
        return instance;
    }

    public void insertUserData(User newUser){
        db.collection("users")
                .add(newUser);
    }

    private void listenUserCollection(){
        db.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange document : queryDocumentSnapshots.getDocumentChanges()){
                            final User currentUser = document.getDocument().toObject(User.class);
                            if(!users.contains(currentUser)) users.add(currentUser);
                        }
                    }
                });
    }

    private void loadUserData(){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                User currentUser = document.toObject(User.class);
                                if(!users.contains(currentUser)) users.add(currentUser);
                            }
                        }
                    }
                });
    }

    public User getUserByUsername(String username){
        for (User user: users) {
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public Vector<User> getAllUsers(){
        return users;
    }

    private String getSaltFromUserByUsername(String username){
        final User selectedUser = getUserByUsername(username);
        if(selectedUser == null) return "";
        return selectedUser.getSalt();
    }

    private String getHashedPasswordFromUserByUsername(String username){
        final User selectedUser = getUserByUsername(username);
        if(selectedUser == null) return "";
        return selectedUser.getPassword();
    }

    private byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public User getUserByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final byte[] salt = fromHex(getSaltFromUserByUsername(username));
        final byte[] hashedPassword = fromHex(getHashedPasswordFromUserByUsername(username));
        Log.d("Salt",salt.toString());
        if(salt == null || hashedPassword == null) return null;
        if(!validateLoginUserByPassword(password,salt,hashedPassword)) return  null;
        return getUserByUsername(username);
    }

    private boolean validateLoginUserByPassword(final String password, final byte[] salt, final byte[] hashedPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, hashedPassword.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
        int diff = hashedPassword.length ^ testHash.length;
        for(int i = 0; i < hashedPassword.length && i < testHash.length; i++)
        {
            diff |= hashedPassword[i] ^ testHash[i];
        }
        return diff == 0;
    }

}
