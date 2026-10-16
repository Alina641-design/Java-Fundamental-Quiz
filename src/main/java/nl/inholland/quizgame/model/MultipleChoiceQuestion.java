package nl.inholland.quizgame.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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

    @Override
    protected boolean isCorrectAnswer(Object answer) {
        return correctAnswer.equals(answer);
    }
    @Override
    public List<AnswerOption> getAnswerOptions() {
        List<String> answerChoices = getChoices();
        if ("random".equals(choicesOrder)) {
            Collections.shuffle(answerChoices);
        }
        List<AnswerOption> options = new ArrayList<>();
        for (String choice : answerChoices) {
            options.add(new AnswerOption(choice, choice));
        }
        return options;
    }
}
