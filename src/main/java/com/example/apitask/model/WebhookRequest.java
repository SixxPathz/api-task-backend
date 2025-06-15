
package com.example.apitask.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WebhookRequest {

    @JsonProperty("data")
    private String data;


    public WebhookRequest() {
    }


    public WebhookRequest(String data) {
        this.data = data;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }
}