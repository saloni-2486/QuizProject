import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultsHistoryController {

    @FXML
    private ListView<String> resultsList;

    @FXML
    public void initialize() {

        String query =
            "SELECT q.quiz_name, r.score, r.total_questions " +
            "FROM results r " +
            "JOIN quizzes q ON r.quiz_id = q.quiz_id " +
            "ORDER BY r.result_id DESC";

        try {

            Connection connection =
                DatabaseConnection.connect();

            PreparedStatement statement =
                connection.prepareStatement(query);

            ResultSet result =
                statement.executeQuery();

            while (result.next()) {

                String quizName =
                    result.getString("quiz_name");

                int score =
                    result.getInt("score");

                int total =
                    result.getInt("total_questions");

                resultsList.getItems().add(
                    quizName + "        " +
                    score + " / " + total
                );
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backToHome() {

        try {

            Parent root =
                FXMLLoader.load(
                    getClass().getResource("Home.fxml")
                );

            Stage stage =
                (Stage) resultsList.getScene().getWindow();

            Scene scene =
                new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}