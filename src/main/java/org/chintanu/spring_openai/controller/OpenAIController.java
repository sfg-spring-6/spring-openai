package org.chintanu.spring_openai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.chintanu.spring_openai.model.Capital;
import org.chintanu.spring_openai.model.CapitalInfo;
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

    @GetMapping("/capital")
    public ResponseEntity<String> getCapital(@RequestBody String stateOrCountry) {

        String answer = openAIService.getCapital(stateOrCountry);

        return ResponseEntity.ok(answer);
    }

    @GetMapping("/capitaljson")
    public ResponseEntity<Capital> getCapitalJson(@RequestBody String stateOrCountry) throws JsonProcessingException {

        Capital cap = openAIService.getCapitalJson(stateOrCountry);

        return ResponseEntity.ok(cap);
    }

    @GetMapping("/capitalinfo")
    public ResponseEntity<CapitalInfo> getCapitalInfo(@RequestBody String stateOrCountry) throws JsonProcessingException {

        CapitalInfo cap = openAIService.getCapitalWithInfoJson(stateOrCountry);

        return ResponseEntity.ok(cap);
    }
}
