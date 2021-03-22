package com.example.aneka.validations;

public class AuthValidation {

    public static boolean checkUsernameAndPasswordIsEmpty(final String username, final String password){
        if(username.equals("") || password.equals("")) return true;
        return false;
    }

    public static boolean checkPasswordMatchWithConfirmPassword(final String password, final String confirmPassword){
        if(password.equals(confirmPassword)) return true;
        return  false;
    }
}
