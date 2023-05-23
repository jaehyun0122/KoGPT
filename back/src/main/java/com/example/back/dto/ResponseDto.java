package com.example.back.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * prompt(사용자 전송 메시지)에 대한 응답 결과
 */
@Getter
@ToString
public class ResponseDto {
    private String id;
    private List<GenerationDto> generations;
    private UsageDto usage;
}
