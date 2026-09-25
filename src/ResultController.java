import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResultController {

    @FXML
    private javafx.scene.control.Label scoreLabel;

    @FXML
    private javafx.scene.control.Label messageLabel;

    private int score;
    private int totalQuestions;
    private int quizId;

    public void setResult(int score, int totalQuestions, int quizId) {

        this.score = score;
        this.totalQuestions = totalQuestions;
        this.quizId = quizId;

        scoreLabel.setText(
            "Your Score: " + score + " / " + totalQuestions
        );

        if (score == totalQuestions) {
            messageLabel.setText("Perfect Score! 🎉");
        } else if (score >= totalQuestions / 2) {
            messageLabel.setText("Good job! Keep it up!");
        } else {
            messageLabel.setText("Keep practicing!");
        }
    }

    public void playAgain() {

        try {

            FXMLLoader loader =
                new FXMLLoader(getClass().getResource("quiz.fxml"));

            Parent root = loader.load();

            QuizController controller = loader.getController();

            controller.setQuizId(quizId);

            Stage stage =
                (Stage) scoreLabel.getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseSubject() {

        try {

            Parent root =
                FXMLLoader.load(getClass().getResource("Subjects.fxml"));

            Stage stage =
                (Stage) scoreLabel.getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goHome() {

    try {

        Parent root =
            FXMLLoader.load(getClass().getResource("Home.fxml"));

        Stage stage =
            (Stage) scoreLabel.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
   }
}