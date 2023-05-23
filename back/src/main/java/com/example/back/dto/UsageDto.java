package com.example.back.dto;

import lombok.Getter;

@Getter
public class UsageDto {
    private int prompt_tokens;
    private int generated_tokens;
    private int total_tokens;
}
