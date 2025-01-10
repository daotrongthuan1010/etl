package com.vn.education.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.opencv.core.Mat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
public class CameraAIService {

    private final FaceDetectionService faceDetectionService;

    public void streamCamera(HttpServletResponse response) throws IOException {
        response.setContentType("multipart/x-mixed-replace; boundary=--frame");
        OutputStream outputStream = response.getOutputStream();

        // Mở camera và nhận diện khuôn mặt
        Mat frame = faceDetectionService.detectFacesFromCamera();

        while (frame != null) {
            // Mã hóa frame thành JPEG
            byte[] imageBytes = faceDetectionService.encodeFrame(frame);

            // Gửi frame tới client
            outputStream.write(("--frame\r\nContent-Type: image/jpeg\r\n\r\n").getBytes());
            outputStream.write(imageBytes);
            outputStream.write("\r\n".getBytes());

            outputStream.flush();

            // Tiếp tục đọc frame mới
            frame = faceDetectionService.detectFacesFromCamera();
        }
    }
}

