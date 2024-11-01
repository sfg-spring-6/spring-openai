package org.chintanu.spring_openai.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.chintanu.spring_openai.model.Capital;
import org.chintanu.spring_openai.model.CapitalInfo;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService{

    @Value("classpath:templates/getcapital.st")
    private Resource getCapital;

    @Value("classpath:templates/get-capital-json.st")
    private Resource getCapitalJson;

    private final ChatModel chatModel;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String getAnswer(String question) {

        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getContent();
    }

    @Override
    public String getCapital(String stateOrCountry) {

        PromptTemplate promptTemplate = new PromptTemplate(getCapital);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", stateOrCountry));

        ChatResponse response = chatModel.call(prompt);

        //System.out.println(response.getResult().getOutput().getContent());

        return response.getResult().getOutput().getContent();
    }

    @Override
    public Capital getCapitalJson(String stateOrCountry) throws JsonProcessingException {

        BeanOutputConverter<Capital> converter = new BeanOutputConverter<>(Capital.class);
        String schema = converter.getFormat();

        System.out.println(schema);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalJson);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", stateOrCountry, "format", schema));

        ChatResponse response = chatModel.call(prompt);

        String res = response.getResult().getOutput().getContent();

        System.out.println("Response : " + res);

        return converter.convert(res);
    }

    @Override
    public CapitalInfo getCapitalWithInfoJson(String stateOrCountry) throws JsonProcessingException {

        BeanOutputConverter<CapitalInfo> converter = new BeanOutputConverter<>(CapitalInfo.class);
        String schema = converter.getFormat();

        System.out.println(schema);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalJson);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", stateOrCountry, "format", schema));

        ChatResponse response = chatModel.call(prompt);

        String res = response.getResult().getOutput().getContent();

        System.out.println("Response : " + res);

        return converter.convert(res);
    }
}
