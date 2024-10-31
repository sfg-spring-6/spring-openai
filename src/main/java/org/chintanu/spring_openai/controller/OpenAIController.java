package org.chintanu.spring_openai.controller;

import lombok.RequiredArgsConstructor;
import org.chintanu.spring_openai.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenAIController {

    private final OpenAIService openAIService;

    @GetMapping("/answer")
    public ResponseEntity<String> getAnswer(@RequestBody String question) {

        String answer = openAIService.getAnswer(question);

        return ResponseEntity.ok(answer);
    }
}
