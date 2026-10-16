package nl.inholland.quizgame.model;
import java.util.List;

public class BooleanQuestion extends Question {
    private String labelTrue;
    private String labelFalse;
    private boolean corectAnswer;

    public BooleanQuestion(String name, String title, boolean isRequired, String labelTrue, String labelFalse, boolean corectAnswer) {
        super(name, title, isRequired);
        this.labelTrue = labelTrue;
        this.labelFalse = labelFalse;
        this.corectAnswer = corectAnswer;
    }
    public String getLabelTrue() {
        return labelTrue;
    }
    public String getLabelFalse() {
        return labelFalse;
    }
    public boolean getCorrectAnswer() {
        return corectAnswer;
    }

    @Override
    protected boolean isCorrectAnswer(Object answer) {
        return Boolean.valueOf(getCorrectAnswer()).equals(answer);
    }
    @Override
    public List<AnswerOption> getAnswerOptions() {
        return List.of(
                new AnswerOption(labelTrue, true),
                new AnswerOption(labelFalse, false));
    }
}
