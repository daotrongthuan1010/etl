package com.vn.education.utils.common;

import java.sql.Types;

public class TypePrimaryKey {

    /**
     * Xử lý giá trị khóa chính dựa trên kiểu dữ liệu.
     *
     * @param value   Giá trị của khóa chính.
     * @param sqlType Kiểu dữ liệu của khóa chính (dựa trên java.sql.Types).
     * @return Chuỗi giá trị đã được xử lý.
     */
    public static String formatPrimaryKeyValue(Object value, int sqlType) {
        if (value == null) {
            throw new IllegalArgumentException("Primary key value cannot be null.");
        }

        switch (sqlType) {
            case Types.VARCHAR:
            case Types.CHAR:
            case Types.NVARCHAR:
            case Types.NCHAR:
            case Types.LONGVARCHAR:
                return "'" + value.toString().replace("'", "''") + "'";

            case Types.INTEGER:
            case Types.BIGINT:
            case Types.SMALLINT:
            case Types.TINYINT:
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.DECIMAL:
            case Types.NUMERIC:
                return value.toString();

            case Types.BOOLEAN:
            case Types.BIT:
                return ((Boolean) value) ? "1" : "0";

            default:
                throw new IllegalArgumentException("Unsupported SQL type: " + sqlType);
        }

    }
}
