package com.vn.education.batch;

import org.opencv.core.Mat;
import org.opencv.core.Core;

public class OpenCVTest {

    static {
        try {
            String libraryPath = System.getProperty("user.dir") + "\\src\\main\\resources\\opencv\\opencv_java4100.dll";
            System.load(libraryPath);
            System.out.println("OpenCV loaded successfully from: " + libraryPath);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load OpenCV native library: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("OpenCV Version: " + Core.VERSION);
        Mat mat = new Mat();
        System.out.println("Matrix created: " + mat.empty());
    }
}
