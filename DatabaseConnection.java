
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/java_exercice";
        String user = "postgres";
        String password = "Yahya123.";  // <-- update this

        return DriverManager.getConnection(url, user, password);
    }
}