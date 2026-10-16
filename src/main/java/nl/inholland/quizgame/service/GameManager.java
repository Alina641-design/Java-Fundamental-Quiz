package nl.inholland.quizgame.service;
import nl.inholland.quizgame.model.Question;
import nl.inholland.quizgame.model.Quiz;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameManager {
    private static final GameManager instance = new GameManager();
    private String playerName = "";
    private Quiz quiz;
    private int currentQuestionIndex;
    private final IntegerProperty score = new SimpleIntegerProperty(0);
    private boolean answerSubmitted;

    private GameManager() {

    }
    public static GameManager getInstance() {
        return instance;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        currentQuestionIndex = 0;
        score.set(0);
        answerSubmitted = false;
    }

    public Question getCurrentQuestion() {
        return quiz.getPages().get(currentQuestionIndex).getQuestion();
    }
    public ReadOnlyIntegerProperty scoreProperty() {
        return score;
    }

    public int getScore() {
        return score.get();
    }
    public void submitAnswer(Object answer) {
        if (answerSubmitted) {
            return;
        }
        int points = getCurrentQuestion().calculateScore(answer);
        answerSubmitted = true;
        score.set(score.get() + points);
    }
}
