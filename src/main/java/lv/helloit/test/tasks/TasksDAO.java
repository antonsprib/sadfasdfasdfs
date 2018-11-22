package lv.helloit.test.tasks;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TasksDAO {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://ec2-54-246-85-234.eu-west-1.compute.amazonaws.com:5432/d38fg66uk8rvv9?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

    //  Database credentials
    private static final String USER = "ztacdohjspyclv";
    private static final String PASS = "3d0cc2c4883e31c3a81df63bae6235dd3812609b831da382a3737b20df5e5aad";

    private Connection connection;

    public TasksDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAll() {
        ResultSet rs = executeQuery("SELECT * from M_TASKS");
        List<Task> result = new ArrayList<>();

        if (rs != null) {
            try {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Long assignedUserId = rs.getLong("assigned_user_id");

                    result.add(new Task(id, title, description, assignedUserId));
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public Optional<Task> getById(Long id) {
        ResultSet rs = executeQuery("SELECT * from M_TASKS WHERE id = " + id);

        if (rs != null) {
            try {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Long assignedUserId = rs.getLong("assigned_user_id");
                    rs.close();

                    return Optional.of(new Task(id, title, description, assignedUserId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }

    public void insert(Task task) {
        // todo implement
    }

    public void delete(Long id) {
        // todo implement
    }

    public void update(Long taskId, Task task) {
        // todo implement
    }

    private ResultSet executeQuery(String query) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
