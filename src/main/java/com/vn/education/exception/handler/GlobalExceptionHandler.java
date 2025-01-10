//package com.vn.education.exception.handler;
//
//import com.vn.education.exception.DataException;
//import com.vn.education.utils.common.data.MessageProperties;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    private String getCurrentTimestamp() {
//        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
//    }
//
//    @ExceptionHandler(ClassNotFoundException.class)
//    public ResponseEntity<DataException> handleDataException(ClassNotFoundException ex) {
//        return new ResponseEntity<>(new DataExceptio
//                .dateTime(getCurrentTimestamp())
//                .message(MessageProperties.NOT_FOUND)
//                .status(HttpStatus.NOT_FOUND.toString())
//                .build(),
//                HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<DataException> handleClassNotFoundException(RuntimeException ex) {
//        return new ResponseEntity<>(DataException.builder()
//                .dateTime(getCurrentTimestamp())
//                .message(MessageProperties.NOT_FOUND)
//                .status(HttpStatus.NOT_FOUND.toString())
//                .build(),
//                HttpStatus.NOT_FOUND);
//    }
//}
