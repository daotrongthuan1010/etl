package com.vn.education.utils.common;

import com.vn.education.utils.common.data.ColumnMetadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StructTable {

    public static Set<ColumnMetadata> getTableMetadata(String tableName, Connection connection) throws SQLException {
        Set<ColumnMetadata> columnMetadataList = new HashSet<>();

        // Sử dụng Set để lưu danh sách khóa chính
        Set<String> primaryKeys = new HashSet<>();
        DatabaseMetaData dbMetaData = connection.getMetaData();

        // Lấy danh sách khóa chính
        try (ResultSet pkResultSet = dbMetaData.getPrimaryKeys(null, null, tableName)) {
            while (pkResultSet.next()) {
                primaryKeys.add(pkResultSet.getString("COLUMN_NAME"));
            }
        }

        // Lấy thông tin các cột trong bảng
        try (ResultSet columns = dbMetaData.getColumns(null, null, tableName, null)) {
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                boolean isNullable = "YES".equalsIgnoreCase(columns.getString("IS_NULLABLE"));
                boolean isPrimaryKey = primaryKeys.contains(columnName);

                // Sử dụng Builder của Lombok để tạo đối tượng ColumnMetadata
                ColumnMetadata columnMetadata = ColumnMetadata.builder()
                        .columnName(columnName)
                        .dataType(dataType)
                        .columnSize(columnSize)
                        .isNullable(isNullable)
                        .isPrimaryKey(isPrimaryKey)
                        .build();

                columnMetadataList.add(columnMetadata);
            }
        }

        return columnMetadataList;
    }



}
