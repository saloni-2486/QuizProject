import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class QuizController {

    @FXML
    private Label questionLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private RadioButton option4;

    @FXML
    private ToggleGroup options;

    @FXML
    private javafx.scene.control.ProgressBar progressBar;

    @FXML
    private Label questionNumberLabel; 

    private java.util.ArrayList<Question> questions;
    private int currentQuestion = 0;
    private int quizId;
    private int score = 0;
    private Timeline timer;
    private int timeLeft = 120;

    public void initialize() {

    }

    private void startTimer() {

    timeLeft = 120;

    timer = new Timeline(
        new KeyFrame(Duration.seconds(1), event -> {

            timeLeft--;

            int minutes = timeLeft / 60;
            int seconds = timeLeft % 60;

            timerLabel.setText(
                String.format("TIME LEFT: %02d:%02d", minutes, seconds)
            );

            if (timeLeft <= 0) {
                timer.stop();
                finishQuiz();
            }
        })
    );

    timer.setCycleCount(Timeline.INDEFINITE);
    timer.play();
}

    private void finishQuiz() {

    if (timer != null) {
        timer.stop();
    }

    DatabaseConnection.saveResult(
        quizId,
        score,
        questions.size()
    );

    try {

        FXMLLoader loader =
            new FXMLLoader(getClass().getResource("Result.fxml"));

        Parent root = loader.load();

        ResultController controller =
            loader.getController();

        controller.setResult(
            score,
            questions.size(),
            quizId
        );

        Stage stage =
            (Stage) questionLabel.getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void setQuizId(int quizId) {

    this.quizId = quizId;
    questions = DatabaseConnection.getQuestions(quizId);

    currentQuestion = 0;
    score = 0;

    showQuestion();
    startTimer();
}

    private void showQuestion() {

        Question q = questions.get(currentQuestion);

        questionNumberLabel.setText(
    "Question " + (currentQuestion + 1) + " of " + questions.size()
);
       progressBar.setProgress(
    (double) (currentQuestion + 1) / questions.size()
);
       questionLabel.setText(q.getQuestionText());
       
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());

        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
    }

    public void submitAnswer() {

        int selectedOption = 0;

        if (option1.isSelected()) {
            selectedOption = 1;
        } else if (option2.isSelected()) {
            selectedOption = 2;
        } else if (option3.isSelected()) {
            selectedOption = 3;
        } else if (option4.isSelected()) {
            selectedOption = 4;
        }

        if (selectedOption == 0) {
            System.out.println("Please select an answer!");
            return;
        }

        Question q = questions.get(currentQuestion);

        if (selectedOption == q.getCorrectOption()) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong!");
        }

        currentQuestion++;

        if (currentQuestion < questions.size()) {
            showQuestion();
        } else {

        finishQuiz();
   }
    }
}