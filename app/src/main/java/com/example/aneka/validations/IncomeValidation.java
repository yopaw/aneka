package com.example.aneka.validations;

import java.util.Vector;

public class IncomeValidation {

    public static boolean checkIncomeNames(final Vector<String> incomeNames, final String newIncomeName){
        if(incomeNames.contains(newIncomeName)) return true;
        return false;
    }

}
