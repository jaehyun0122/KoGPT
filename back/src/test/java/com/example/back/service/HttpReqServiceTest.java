package com.example.back.service;

import com.example.back.dto.RequestDto;
import com.example.back.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HttpReqServiceTest {
    @Value("${info.token}")
    private String token;
    @Value("${info.url}")
    private String url;

    @Test
    void httpReqTest() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        RequestDto requestDto = RequestDto.builder()
                .prompt("의료 스타트업으로 구성된 원격의료산업협의회가 10월부터 열리는 국정감사 시기에 맞춰 국회와 정부에 비대면 진료법 근거 마련을 촉구하는 정책제안서를 제출한다. 코로나19 사태에 비대면 진료의 한시 허용으로 원격 진료, 의약품 배송 등 서비스가 속속 등장하는 가운데 제도화 논의를 서둘러야 한다는 목소리가 높아질 것으로 전망된다. 코리아스타트업포럼 산하 원격의료산업협의회는 '위드(with) 코로나' 방역 체계 전환을 염두에 두고 비대면 진료 제도화 촉구를 위한 공동 대응 작업을 추진하고 있다. 협의회는 닥터나우, 엠디스퀘어, SH바이오, 메디버디 등 의료 스타트업 13개사로 구성됐다. 협의회는 국정감사 시기를 겨냥해 국회와 주무 부처인 보건복지부에 비대면 진료의 법적 근거 마련을 촉구할 방침이다. 이를 위해 주요 의원실과 관련 의견을 교환하고 있다. 협의회는 궁극적으로 의료법과 약사법 개정이 필요하지만 의료법 테두리 안에서 시행령 개정 등으로도 비대면 진료 가능성과 대상·의료기관 등을 구체화할 수 있다는 복안이다. 복지부 장관령으로 비대면 진료 기간을 명시하는 방안 등을 통해 사업 리스크도 줄일 수 있다. 올해 안에 국내 방역체계 패러다임이 바뀔 것으로 예상되는 점도 비대면 진료 제도화의 필요성을 높이고 있다. 최근 코로나19 백신 접종이 속도를 내면서 방역 당국은 위드 코로나 방역체계 전환을 고려하고 있다. 인구 대비 백신 접종 완료율이 70%가 되는 오는 10월 말에는 전환 논의가 수면 위로 뜰 것으로 보인다.\n" +
                        "정책제안서를 제출하는 시기는 언제인가?:")
                .tokens(128)
                .temperature(0.7f)
                .n(2)
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
    }

    @Test
    void responseMapping() throws JsonProcessingException {
        String response = "{\"id\":\"aaf3e6ab-a735-40f3-8f82-d95ee9bb2d14\",\"generations\":[{\"text\":\"긍정\",\"tokens\":2},{\"text\":\"긍정\",\"tokens\":2}],\"usage\":{\"prompt_tokens\":78,\"generated_tokens\":2,\"total_tokens\":80}}";

        ObjectMapper om = new ObjectMapper();
        ResponseDto responseDto = om.readValue(response, ResponseDto.class);

        System.out.println(responseDto);
        responseDto.getGenerations().stream().map(data->data.getText()).forEach(System.out::println);

    }

}