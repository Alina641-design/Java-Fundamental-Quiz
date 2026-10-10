package nl.inholland.quizgame.model;

public class QuizPage {
    private int timeLimit;
    private Question question;
    public QuizPage (int timeLimit, Question question) {
        this.timeLimit = timeLimit;
        this.question = question;
    }
    public int getTimeLimit() {
        return timeLimit;
    }
    public Question getQuestion() {
        return question;
    }
}
