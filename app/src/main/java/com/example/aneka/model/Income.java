package com.example.aneka.model;

import java.util.Date;

public class Income {

    private String incomeName;
    private Date insertedDate;
    private Integer incomeValue;

    public Income(){

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

    public Date getInsertDate() {
        return insertedDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertedDate = insertDate;
    }

    public Integer getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(Integer incomeValue) {
        this.incomeValue = incomeValue;
    }
}
