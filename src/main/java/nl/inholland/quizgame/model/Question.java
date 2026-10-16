package nl.inholland.quizgame.model;
import java.util.List;

public abstract class Question {
    private String name;
    private String title;
    private boolean isRequired;


    public Question (String name, String title, boolean isRequired) {
        this.name = name;
        this.title = title;
        this.isRequired = isRequired;
    }
    public String getName() {
        return name;
    }
    public String getTitle() {
        return title;
    }
    public boolean isRequired() {
        return isRequired;
    }

    public final int calculateScore(Object answer) {
        if (answer == null) {
            return 0;
        }
        if (isCorrectAnswer(answer)) {
            return 1;
        }
        return 0;
    }

    protected abstract boolean isCorrectAnswer(Object answer);
    public abstract List<AnswerOption> getAnswerOptions();
}
