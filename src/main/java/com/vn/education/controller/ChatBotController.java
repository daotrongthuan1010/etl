package com.vn.education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
public class ChatBotController {

    private final RestTemplate restTemplate;

    @GetMapping("/webcam")
    public String getWebCamPage() {
        // URL của trang web chứa demo webcam
        String apiUrl = "https://vladmandic.github.io/face-api/demo/webcam.html";

        // Sử dụng RestTemplate để gọi trang web
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        // Trả về nội dung HTML từ trang web
        return response.getBody();
    }
}