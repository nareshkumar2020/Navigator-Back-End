package com.naresh.navigator.model;

public class AutocompleteResponse {

    private String status;
    private Prediction[] predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Prediction[] getPredictions() {
        return predictions;
    }

    public void setPredictions(Prediction[] predictions) {
        this.predictions = predictions;
    }
}
