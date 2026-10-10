package nl.inholland.quizgame.model;

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
}
