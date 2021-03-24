package com.example.aneka.model;

import java.util.Date;

public class IncomeTransaction {

    private String id;
    private String incomeName;
    private Integer incomeValue;
    private Date transactionDate;
    private String transactionNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIncomeValue(Integer incomeValue) {
        this.incomeValue = incomeValue;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    public IncomeTransaction(String id, String incomeName, Integer incomeValue, Date transactionDate, String transactionNote) {
        this.id = id;
        this.incomeName = incomeName;
        this.incomeValue = incomeValue;
        this.transactionDate = transactionDate;
        this.transactionNote = transactionNote;
    }

    public IncomeTransaction(String incomeName, Integer incomeValue, Date transactionDate, String transactionNote) {
        this.id = id;
        this.incomeName = incomeName;
        this.incomeValue = incomeValue;
        this.transactionDate = transactionDate;
        this.transactionNote = transactionNote;
    }

    public IncomeTransaction(){

    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
