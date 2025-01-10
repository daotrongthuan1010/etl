package com.vn.education.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenCVLoader {

    private static boolean loaded = false;

    public static void loadOpenCV() {
        if (!loaded) {
            try {
                String libraryPath = System.getProperty("user.dir") + "\\src\\main\\resources\\opencv\\opencv_java4100.dll";
                System.load(libraryPath);
                log.info("OpenCV loaded successfully from: " + libraryPath);
                loaded = true;
            } catch (UnsatisfiedLinkError e) {
                log.info("Failed to load OpenCV native library: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
