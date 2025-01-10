package com.vn.education;

import com.vn.education.controller.OpenCVLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EducationApplication {
	static {
		try {
			OpenCVLoader.loadOpenCV();
			String libraryPath = System.getProperty("user.dir") + "\\src\\main\\resources\\opencv\\opencv_java4100.dll";
			System.load(libraryPath);
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Failed to load OpenCV native library: " + e.getMessage());
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}

}
