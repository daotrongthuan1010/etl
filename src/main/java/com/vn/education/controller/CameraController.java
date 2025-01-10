package com.vn.education.controller;

import com.vn.education.service.CameraAIService;
import com.vn.education.service.CameraService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/camera")
public class CameraController {

    private final CameraService cameraService = new CameraService();

    private final CameraAIService cameraAIService;

    @GetMapping("/live")
    public void liveStream(HttpServletResponse response) {
        System.load("C:/Users/daotr/Downloads/education/education/src/main/resources/opencv/opencv_java4100.dll");


        cameraService.streamCamera(response);
    }

    @GetMapping("/live/ai")
    public void liveStreamAI(HttpServletResponse response) throws IOException {
        System.load("C:/Users/daotr/Downloads/education/education/src/main/resources/opencv/opencv_java4100.dll");


        cameraAIService.streamCamera(response);
    }
}
