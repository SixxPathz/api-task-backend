package com.example.apitask.controller;

import com.example.apitask.model.WebhookRequest;
import com.example.apitask.model.SortedWordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebhookController {


    @PostMapping("/webhook")
    public ResponseEntity<SortedWordResponse> processWebhook(@RequestBody WebhookRequest request) {


        String inputString = (request != null && request.getData() != null) ? request.getData() : "";


        if (inputString.isEmpty()) {
            System.out.println("Input string is empty or null. Returning empty sorted word.");
            return ResponseEntity.ok(new SortedWordResponse(Collections.emptyList()));
        }

        List<Character> charList = inputString.chars()
                .mapToObj(c -> (char) c).sorted().toList();


        System.out.println("Sorted character list: " + charList);


        List<String> sortedWordCharacters = charList.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());


        SortedWordResponse response = new SortedWordResponse(sortedWordCharacters);


        System.out.println("Returning response: " + sortedWordCharacters);
        return ResponseEntity.ok(response);
    }
}