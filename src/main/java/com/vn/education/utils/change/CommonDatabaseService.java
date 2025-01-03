package com.vn.education.utils.change;

import com.vn.education.dto.ChangeEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CommonDatabaseService implements DatabaseService {

    private String dbUrlValue;

    private String dbUserValue;

    private String dbPasswordValue;

    @Override
    public void updateTargetDatabase(ChangeEvent changeEvent) {
        if (changeEvent.getAfter() != null) {
            String tableName = changeEvent.getTableName();
            String updateSql = buildUpdateSql(tableName, changeEvent.getAfter());
            executeUpdateSql(updateSql);
        }
    }

    @Override
    public void insertIntoTargetDatabase(ChangeEvent changeEvent) {
        if (changeEvent.getAfter() != null) {
            String tableName = changeEvent.getTableName();
            String insertSql = buildInsertSql(tableName, changeEvent.getAfter());
            executeUpdateSql(insertSql);
        }
    }

    @Override
    public void deleteFromTargetDatabase(ChangeEvent changeEvent) {
        if (changeEvent.getBefore() != null) {
            String tableName = changeEvent.getTableName();
            Long id = (Long) changeEvent.getBefore().get("id");
            String deleteSql = "DELETE FROM " + tableName + " WHERE id = " + id;
            executeUpdateSql(deleteSql);
        }
    }

    private String buildUpdateSql(String tableName, Map<String, Object> data) {
        StringBuilder setClause = new StringBuilder();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            setClause.append(entry.getKey())
                    .append(" = '")
                    .append(entry.getValue())
                    .append("', ");
        }
        setClause.setLength(setClause.length() - 2);

        return "UPDATE " + tableName + " SET " + setClause.toString() + " WHERE id = " + data.get("id");
    }

    private String buildInsertSql(String tableName, Map<String, Object> data) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            columns.append(entry.getKey()).append(", ");
            values.append("'").append(entry.getValue()).append("', ");
        }

        columns.setLength(columns.length() - 2);
        values.setLength(values.length() - 2);

        return "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES (" + values.toString() + ")";
    }

    private void executeUpdateSql(String sql) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(dbUrlValue, dbUserValue, dbPasswordValue);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
