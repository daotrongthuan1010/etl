package com.vn.education.service;

import jakarta.servlet.http.HttpServletResponse;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.springframework.stereotype.Service;

import java.io.OutputStream;




@Service
public class CameraService {
    private final VideoCapture camera;

    public CameraService() {
        this.camera = new VideoCapture(0); // Mở camera mặc định
        if (!camera.isOpened()) {
            throw new RuntimeException("Không thể mở camera!");
        }
    }

    /**
     * Phát live video từ camera.
     *
     * @param response HTTP response để truyền dữ liệu.
     */
    public void streamCamera(HttpServletResponse response) {
        try {
            // Thiết lập Content-Type cho HTTP response
            response.setContentType("multipart/x-mixed-replace; boundary=--frame");
            OutputStream outputStream = response.getOutputStream();

            Mat frame = new Mat();

            while (true) {
                // Đọc frame từ camera
                if (!camera.read(frame) || frame.empty()) {
                    break;
                }

                // Mã hóa frame thành JPEG
                byte[] imageBytes = encodeFrame(frame);

                // Gửi frame tới client
                outputStream.write(("--frame\r\nContent-Type: image/jpeg\r\n\r\n").getBytes());
                outputStream.write(imageBytes);
                outputStream.write("\r\n".getBytes());

                outputStream.flush();

                // Giảm tốc độ frame (33ms ~ 30fps)
                Thread.sleep(33);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng camera khi hoàn tất
            if (camera.isOpened()) {
                camera.release();
            }
        }
    }

    /**
     * Mã hóa frame từ OpenCV thành ảnh JPEG.
     *
     * @param frame Frame từ camera.
     * @return Byte array của ảnh JPEG.
     */
    public byte[] encodeFrame(org.bytedeco.opencv.opencv_core.Mat frame) {
        BytePointer buf = new BytePointer();
        boolean success = opencv_imgcodecs.imencode(".jpg", frame, buf);
        if (!success) {
            throw new RuntimeException("Không thể mã hóa frame thành JPEG!");
        }
        byte[] byteArray = new byte[(int) buf.limit()];
        buf.get(byteArray);
        return byteArray;
    }


}
