package nl.inholland.quizgame.model;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> choices;
    private String choicesOrder;
    private String correctAnswer;
    public MultipleChoiceQuestion(String name, String title, boolean isRequired, List<String> choices, String choicesOrder, String correctAnswer) {
        super(name, title, isRequired);
        this.choices = new ArrayList<>(choices);
        this.choicesOrder = choicesOrder;
        this.correctAnswer = correctAnswer;
    }
    public List<String> getChoices() {
        return new ArrayList<>(choices);
    }
    public String getChoicesOrder() {
        return choicesOrder;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
