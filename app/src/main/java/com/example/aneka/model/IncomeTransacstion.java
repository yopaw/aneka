package com.example.aneka.model;

import java.util.Date;

public class IncomeTransacstion {
    private String incomeName;
    private int incomeValue;
    private Date transactionDate;

    public IncomeTransacstion(){

    }

    public IncomeTransacstion(String incomeName, int incomeValue, Date transactionDate) {
        this.incomeName = incomeName;
        this.incomeValue = incomeValue;
        this.transactionDate = transactionDate;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public int getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(int incomeValue) {
        this.incomeValue = incomeValue;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
