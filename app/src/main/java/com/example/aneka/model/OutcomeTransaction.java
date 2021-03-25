package com.example.aneka.model;

import java.util.Date;

public class OutcomeTransaction {

    private String id;
    private String outcomeName;
    private Integer outcomeValue;
    private Date transactionDate;
    private String transactionNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOutcomeValue(Integer outcomeValue) {
        this.outcomeValue = outcomeValue;
    }

    public Integer getOutcomeValue() {
        return outcomeValue;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    public OutcomeTransaction(String id, String outcomeName, Integer outcomeValue, Date transactionDate, String transactionNote) {
        this.id = id;
        this.outcomeName = outcomeName;
        this.outcomeValue = outcomeValue;
        this.transactionDate = transactionDate;
        this.transactionNote = transactionNote;
    }

    public OutcomeTransaction(String outcomeName, Integer outcomeValue, Date transactionDate, String transactionNote) {
        this.id = id;
        this.outcomeName = outcomeName;
        this.outcomeValue = outcomeValue;
        this.transactionDate = transactionDate;
        this.transactionNote = transactionNote;
    }

    public OutcomeTransaction(){

    }

    public String getOutcomeName() {
        return outcomeName;
    }

    public void setOutcomeName(String outcomeName) {
        this.outcomeName = outcomeName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
