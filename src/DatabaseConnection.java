import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection connect() {

        String url = "jdbc:postgresql://localhost:5432/QuizProject";
        String user = "postgres";
        String password = "YOUR_PASSWORD_HERE";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");
            return connection;

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
   public static java.util.ArrayList<Question> getQuestions(int quizId) {

    java.util.ArrayList<Question> questions = new java.util.ArrayList<>();

    String query =
        "SELECT q.question_text, " +
        "o1.option_text AS option1, " +
        "o2.option_text AS option2, " +
        "o3.option_text AS option3, " +
        "o4.option_text AS option4, " +
        "CASE " +
        "WHEN o1.is_correct THEN 1 " +
        "WHEN o2.is_correct THEN 2 " +
        "WHEN o3.is_correct THEN 3 " +
        "WHEN o4.is_correct THEN 4 " +
        "END AS correct_option " +
        "FROM questions q " +
        "JOIN options o1 ON q.question_id = o1.question_id " +
        "JOIN options o2 ON q.question_id = o2.question_id " +
        "JOIN options o3 ON q.question_id = o3.question_id " +
        "JOIN options o4 ON q.question_id = o4.question_id " +
        "WHERE q.quiz_id = ? " +
        "AND o1.option_id < o2.option_id " +
        "AND o2.option_id < o3.option_id " +
        "AND o3.option_id < o4.option_id " +
        "ORDER BY q.question_id";

    try {

        Connection connection = connect();

        java.sql.PreparedStatement statement =
    connection.prepareStatement(query);

statement.setInt(1, quizId);

java.sql.ResultSet result = statement.executeQuery();

        while (result.next()) {

            Question question = new Question(
                result.getString("question_text"),
                result.getString("option1"),
                result.getString("option2"),
                result.getString("option3"),
                result.getString("option4"),
                result.getInt("correct_option")
            );

            questions.add(question);
        }

        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return questions;
} 
   public static java.util.ArrayList<String> getQuizNames() {

    java.util.ArrayList<String> quizNames = new java.util.ArrayList<>();

    String query = "SELECT quiz_name FROM quizzes ORDER BY quiz_id";

    try {

        Connection connection = connect();

        java.sql.Statement statement = connection.createStatement();

        java.sql.ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            quizNames.add(result.getString("quiz_name"));
        }

        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return quizNames;
}
      public static void saveResult(int quizId, int score, int totalQuestions) {

    String query =
        "INSERT INTO results (user_id, quiz_id, score, total_questions) " +
        "VALUES (?, ?, ?, ?)";

    try {

        Connection connection = connect();

        java.sql.PreparedStatement statement =
            connection.prepareStatement(query);

        statement.setInt(1, 1);
        statement.setInt(2, quizId);
        statement.setInt(3, score);
        statement.setInt(4, totalQuestions);

        statement.executeUpdate();

        connection.close();

        System.out.println("Result saved successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
}    
}