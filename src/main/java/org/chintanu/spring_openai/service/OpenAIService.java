package org.chintanu.spring_openai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.chintanu.spring_openai.model.Capital;
import org.chintanu.spring_openai.model.CapitalInfo;

public interface OpenAIService {

    String getAnswer(String question);

    String getCapital(String stateOrCountry);

    Capital getCapitalJson(String stateOrCountry) throws JsonProcessingException;

    CapitalInfo getCapitalWithInfoJson(String stateOrCountry) throws JsonProcessingException;
}
