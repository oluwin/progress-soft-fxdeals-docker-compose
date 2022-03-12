package com.bloomberg.fx.deals.message;

public class FeedbackMessage {
    private String response;

    public FeedbackMessage(String response){
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
