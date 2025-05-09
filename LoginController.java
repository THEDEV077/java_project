import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill all fields");
            return;
        }

        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                messageLabel.setText("✅ Login successful! Welcome " + rs.getString("first_name"));

                // Redirect to test.fxml
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainInterface.fxml"));
                    Scene scene = new Scene(loader.load());
                    Stage stage = (Stage) usernameField.getScene().getWindow(); // Get the current stage
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    messageLabel.setText("❌ Error loading the next page: " + e.getMessage());
                }
            } else {
                messageLabel.setText("❌ Invalid username or password");
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("❌ Database error: " + e.getMessage());
        }
    }

    @FXML
    private void handleCreateAccount() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create_account.fxml"));
            Scene scene = new Scene(loader.load(), 300, 300);
            Stage stage = new Stage();
            stage.setTitle("Create Account");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}