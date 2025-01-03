package com.vn.education.utils.change;

import com.vn.education.dto.ChangeEvent;
import org.json.JSONObject;

import java.util.Map;

public class ChangeDataEvent {

    public static ChangeEvent parseChangeEvent(String value) {
        // Phân tích chuỗi JSON thành đối tượng JSONObject (sử dụng thư viện JSON như Jackson hoặc Gson)
        JSONObject jsonObject = new JSONObject(value);
        String operation = jsonObject.getString("operation");  // 'i', 'u', 'd', 's' (schema change)

        if ("s".equals(operation)) {
            String schema = jsonObject.getString("schema");
            ChangeEvent.builder()
                    .operation(operation)
                    .timestamp(System.currentTimeMillis())
                    .tableName(jsonObject.getString("tableName"))
                    .schema(schema).
                    build();
        } else {
            Map<String, Object> before = jsonObject.optJSONObject("before") != null ? jsonObject.getJSONObject("before").toMap() : null;
            Map<String, Object> after = jsonObject.optJSONObject("after") != null ? jsonObject.getJSONObject("after").toMap() : null;
            ChangeEvent.builder()
                    .operation(operation)
                    .timestamp(System.currentTimeMillis())
                    .tableName(jsonObject.getString("tableName"))
                    .before(before)
                    .after(after)
                            .
                    build();
        }
        return ChangeEvent.builder()
                .build();
    }


    // Xử lý các sự kiện thay đổi dữ liệu (insert, update, delete)
    public static void processChangeEvent(ChangeEvent changeEvent, DatabaseService dbService) {
        if ("u".equals(changeEvent.getOperation())) {
            dbService.updateTargetDatabase(changeEvent);
        } else if ("i".equals(changeEvent.getOperation())) {
            dbService.insertIntoTargetDatabase(changeEvent);
        } else if ("d".equals(changeEvent.getOperation())) {
            dbService.deleteFromTargetDatabase(changeEvent);
        }
    }

}
