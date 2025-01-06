package com.vn.education.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ChangeEvent {
    private String operation;  // 'u' = update, 'i' = insert, 'd' = delete, 's' = schema change
    private Map<String, Object> before;  // Trước khi thay đổi
    private Map<String, Object> after;   // Sau khi thay đổi
    private Long timestamp;
    private String tableName;
    private String schema;

    public ChangeEvent(String operation, Map<String, Object> before, Map<String, Object> after,
                       Long timestamp, String tableName, String schema) {
        this.operation = operation;
        this.before = before;
        this.after = after;
        this.timestamp = timestamp;
        this.tableName = tableName;
        this.schema = schema;
    }
}
