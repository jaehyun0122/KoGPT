package com.example.back.controller;

import com.example.back.dto.GenerationDto;
import com.example.back.response.Response;
import com.example.back.service.HttpReqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class TextController {
    private final HttpReqService httpReqService;

    @PostMapping()
    public ResponseEntity<List<GenerationDto>> getText(@RequestBody Map<String, String> request) throws Exception {
        String response = httpReqService.HttpReq(request);
        List<GenerationDto> responseList = httpReqService.getResponse(response);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
