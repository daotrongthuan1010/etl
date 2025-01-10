package com.vn.education.utils.common;

import java.lang.reflect.Method;

public class ReflectionConverter {

    /**
     * Tìm và gọi phương thức chuyển đổi dữ liệu bằng Reflection.
     *
     * @param converterClass Class chứa các phương thức chuyển đổi.
     * @param methodName     Tên phương thức chuyển đổi (hoặc null nếu muốn auto).
     * @param parameters     Các tham số truyền vào phương thức.
     * @return Kết quả sau khi chuyển đổi.
     * @throws Exception Nếu không tìm thấy phương thức phù hợp.
     */
    public static Object convertData(Class<?> converterClass, String methodName, Object... parameters) throws Exception {
        if (methodName != null) {
            // Nếu có tên phương thức, tìm và gọi phương thức tương ứng
            Method method = findMethod(converterClass, methodName, parameters);
            if (method != null) {
                return method.invoke(null, parameters);
            } else {
                throw new IllegalArgumentException("No matching method found for name: " + methodName);
            }
        } else {
            // Nếu không có tên phương thức, thực hiện auto chuyển đổi (chọn phương thức đầu tiên phù hợp)
            for (Method method : converterClass.getDeclaredMethods()) {
                if (isCompatible(method, parameters)) {
                    return method.invoke(null, parameters);
                }
            }
            throw new IllegalArgumentException("No suitable method found for auto conversion.");
        }
    }

    /**
     * Tìm phương thức trong class dựa trên tên và tham số.
     *
     * @param clazz      Class chứa phương thức.
     * @param methodName Tên phương thức cần tìm.
     * @param parameters Các tham số của phương thức.
     * @return Phương thức phù hợp hoặc null nếu không tìm thấy.
     */
    private static Method findMethod(Class<?> clazz, String methodName, Object... parameters) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(methodName) && isCompatible(method, parameters)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Kiểm tra xem phương thức có tương thích với tham số không.
     *
     * @param method     Phương thức cần kiểm tra.
     * @param parameters Các tham số truyền vào.
     * @return true nếu phương thức tương thích, false nếu không.
     */
    private static boolean isCompatible(Method method, Object... parameters) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != parameters.length) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].isAssignableFrom(parameters[i].getClass())) {
                return false;
            }
        }
        return true;
    }
}
