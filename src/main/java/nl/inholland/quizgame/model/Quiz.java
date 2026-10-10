package nl.inholland.quizgame.model;
import java.util.ArrayList;
import java.util.List;
public class Quiz {
    private String quizId;
    private String title;
    private String description;
    private List<QuizPage> pages;

    public Quiz(String quizId, String title, String description, List<QuizPage> pages) {
        this.quizId = quizId;
        this.title = title;
        this.description = description;
        this.pages = new ArrayList<>(pages);
    }

    public String getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<QuizPage> getPages() {
        return new ArrayList<>(pages);
    }
}

