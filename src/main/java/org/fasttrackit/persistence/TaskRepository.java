package org.fasttrackit.persistence;

import org.fasttrackit.config.DatabaseConfiguration;
import org.fasttrackit.transfer.CreateTaskRequest;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//DAO (Data Access Object)
public class TaskRepository {

    public void createTask(CreateTaskRequest request) throws SQLException {
        //preventing SQL injection using placeholders and PreparedStatement
        String sql = "INSERT INTO task (description, deadline) VALUES (?, ?)";
        //try-with-resources
        try (
        PreparedStatement preparedStatement = DatabaseConfiguration.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setDate(2, Date.valueOf(request.getDeadline()));

            preparedStatement.executeUpdate();
        }
    }

    public void deleteTask(long id) throws SQLException {
        String sql = "DELETE FROM task WHERE id = ?";

        try (PreparedStatement preparedStatement =DatabaseConfiguration.getConnection().prepareStatement(sql) ) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}
