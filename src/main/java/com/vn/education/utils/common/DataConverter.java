package com.vn.education.utils.common;

public class DataConverter {


    public static String convertToCoordinates(int x, int y) {
        return String.format("(%d, %d)", x, y);
    }


    public static double autoConvert(int value) {
        return (double) value;
    }
}
