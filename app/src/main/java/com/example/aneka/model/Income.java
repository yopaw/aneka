package com.example.aneka.model;

import java.util.Date;

public class Income {

    private String id;
    private String incomeName;
    private Date insertedDate;
    private Integer incomeValue;

    public Income(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Income(String id, String incomeName, Date insertedDate, Integer incomeValue) {
        this.id = id;
        this.incomeName = incomeName;
        this.insertedDate = insertedDate;
        this.incomeValue = incomeValue;
    }

    public Income(String incomeName, Date insertDate, Integer incomeValue) {
        this.incomeName = incomeName;
        this.insertedDate = insertDate;
        this.incomeValue = incomeValue;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public Integer getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(Integer incomeValue) {
        this.incomeValue = incomeValue;
    }
}
