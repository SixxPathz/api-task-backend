
package com.example.apitask.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class SortedWordResponse {

    @JsonProperty("word")
    private List<String> word;

    public SortedWordResponse(List<String> word) {
        this.word = word;
    }


    public List<String> getWord() {
        return word;
    }


    public void setWord(List<String> word) {
        this.word = word;
    }
}