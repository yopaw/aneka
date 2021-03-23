package com.example.aneka.model;

import java.util.Date;

public class Outcome {

    private String id;
    private String outcomeName;
    private Integer outcomeValue;
    private Date insertedDate;

    public Outcome(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutcomeName() {
        return outcomeName;
    }

    public void setOutcomeName(String outcomeName) {
        this.outcomeName = outcomeName;
    }

    public Integer getOutcomeValue() {
        return outcomeValue;
    }

    public void setOutcomeValue(Integer outcomeValue) {
        this.outcomeValue = outcomeValue;
    }

    public Date getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Outcome(String id, String outcomeName, int outcomeValue, Date insertedDate) {
        this.id = id;
        this.outcomeName = outcomeName;
        this.outcomeValue = outcomeValue;
        this.insertedDate = insertedDate;
    }

    public Outcome(String outcomeName, int outcomeValue, Date insertedDate) {
        this.id = id;
        this.outcomeName = outcomeName;
        this.outcomeValue = outcomeValue;
        this.insertedDate = insertedDate;
    }
}
