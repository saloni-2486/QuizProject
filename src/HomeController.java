import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    public void startQuiz(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(
                getClass().getResource("Subjects.fxml")
            );

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showResults(ActionEvent event) {

    try {

        Parent root =
            FXMLLoader.load(getClass().getResource("ResultsHistory.fxml"));

        Stage stage =
            (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}