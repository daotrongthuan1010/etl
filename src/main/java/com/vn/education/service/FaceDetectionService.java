package com.vn.education.service;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.*;
import org.opencv.face.FaceRecognizer;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class FaceDetectionService {

    static {
        System.load("C:/Users/daotr/Downloads/education/education/src/main/resources/opencv/opencv_java4100.dll");
    }

    private CascadeClassifier faceCascade;

    private FaceRecognizer faceRecognizer;

    public FaceDetectionService() {
        // Tạo đối tượng CascadeClassifier
        String xmlFile = "haarcascade_frontalface_default.xml";

        // Dùng getClassLoader().getResourceAsStream để lấy InputStream từ resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("opencv/" + xmlFile);

        // Kiểm tra nếu không tìm thấy tệp
        if (inputStream == null) {
            log.info("Tệp XML không thể tìm thấy.");
            return;
        }

        // Tạo file tạm thời để load vào CascadeClassifier
        try {
            // Tạo file tạm thời
            File tempFile = File.createTempFile("haarcascade", ".xml");
            tempFile.deleteOnExit();

            // Ghi dữ liệu từ InputStream vào file tạm thời
            try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Sử dụng file tạm thời để load vào CascadeClassifier
            faceCascade = new CascadeClassifier(tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Lỗi khi tạo file tạm thời.");
        }
//        System.load("C:/Users/daotr/Downloads/education/education/src/main/resources/opencv/opencv_java4100.dll");
//        faceRecognizer = LBPHFaceRecognizer.create();
//        loadRecognizerData();
    }

    public Mat detectFacesFromCamera() {
        // Mở camera
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            log.info("Không thể mở camera.");
            return null;
        }

        Mat frame = new Mat();
        // Đọc frame từ camera liên tục
        while (camera.read(frame)) {
            // Chuyển frame sang grayscale (màu xám)
            Mat grayImage = new Mat();
            Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);

            // Phát hiện khuôn mặt
            MatOfRect faces = new MatOfRect();
            faceCascade.detectMultiScale(grayImage, faces);

            for (Rect rect : faces.toArray()) {
                Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);

                Mat faceRegion = new Mat();


                Size size = new Size(100, 100);
                Mat resizedFace = new Mat();
                Imgproc.resize(faceRegion, resizedFace, size);

                // Tạo IntPointer và DoublePointer
                int[] predictedLabel = new int[1];
                double[] confidence = new double[1];

                // Dự đoán khuôn mặt
                faceRecognizer.predict(resizedFace, predictedLabel, confidence);

              String name = "Unknown"; // Mặc định là "Unknown"

                // Kiểm tra xem có nhận diện được hay không
                if (predictedLabel[0] != -1) {
                    name = "Person " + predictedLabel[0]; // Gán tên người đã nhận diện
                }

                // Ghi tên vào khung
                Imgproc.putText(frame, name, new Point(rect.x, rect.y - 10),
                        Imgproc.FONT_HERSHEY_SIMPLEX, 0.9, new Scalar(0, 255, 0), 2);
            }

            return frame; // Trả về frame với khuôn mặt đã được đánh dấu
        }

        return null;
    }


    // Lưu dữ liệu FaceRecognizer
    private void saveRecognizerData() {
        try {
            faceRecognizer.write("face_data.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tải dữ liệu nhận diện đã lưu
    private void loadRecognizerData() {
        File recognizerData = new File("face_data.xml");
        if (recognizerData.exists()) {
            faceRecognizer.read("face_data.xml");
        }
    }

    // Phương thức lưu ảnh khuôn mặt vào thư mục để nhận diện
    public void saveFaceImage(Mat face, String personName) {
        String filePath = "faces/" + personName + ".jpg";
        Imgcodecs.imwrite(filePath, face);
    }

    public byte[] encodeFrame(Mat frame) {
        // Tạo MatOfByte để lưu trữ dữ liệu byte
        MatOfByte matOfByte = new MatOfByte();

        // Mã hóa frame thành JPEG và lưu vào MatOfByte
        Imgcodecs.imencode(".jpg", frame, matOfByte);

        // Trả về mảng byte chứa dữ liệu JPEG
        return matOfByte.toArray();
    }
}
