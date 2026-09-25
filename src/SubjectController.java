import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SubjectController {

    public void startJavaQuiz(ActionEvent event) {
        startQuiz(event, 1);
    }

    public void startDBMSQuiz(ActionEvent event) {
        startQuiz(event, 2);
    }

    public void startCOAQuiz(ActionEvent event) {
        startQuiz(event, 3);
    }

    public void startElectronicsQuiz(ActionEvent event) {
        startQuiz(event, 4);
    }

    private void startQuiz(ActionEvent event, int quizId) {

        try {

            FXMLLoader loader =
                new FXMLLoader(getClass().getResource("quiz.fxml"));

            Parent root = loader.load();

            QuizController controller = loader.getController();

            controller.setQuizId(quizId);

            Stage stage =
                (Stage) ((Node) event.getSource())
                .getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}