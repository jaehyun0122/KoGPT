package com.example.back.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDto {
    private String prompt; // KoGPT에 전달할 제시어.
    private float temperature; // 0~1 실수. 높을 수록 더 창의적 결과
    private int max_tokens; // 결과 최대 토큰 수.
    private float top_p; // 상위 확률. 0~1. 높을 수록 더 창의적 결과
    private int n; // 응답 결과 개수. 기본 1, 최대 16

    @Builder
    public RequestDto(String prompt, float temperature, int tokens, int n){
        this.prompt = prompt;
        this.temperature = temperature;
        this.max_tokens = tokens;
        this.n = n;
    }

}
