package com.example.back.service;

import com.example.back.dto.GenerationDto;
import com.example.back.dto.ResponseDto;
import com.example.back.dto.RequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class HttpReqService {
    @Value("${info.token}")
    private String token;
    @Value("${info.url}")
    private String url;

    public List<GenerationDto> getResponse(String response) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        ResponseDto responseDto = om.readValue(response, ResponseDto.class);

        return responseDto.getGenerations();
    }

    // Http 요청
    public String HttpReq(Map<String, String> request) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        String prompt = request.get("prompt");
        float temperature = Float.parseFloat(request.get("temperature")) * 0.01f;

        RequestDto requestDto = RequestDto.builder()
                .prompt(prompt)
                .tokens(500)
                .temperature(temperature)
                .n(1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String reqJson = objectMapper.writeValueAsString(requestDto);

        System.out.println(reqJson);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", token)
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(reqJson))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());

        return response.body();
    }
}
