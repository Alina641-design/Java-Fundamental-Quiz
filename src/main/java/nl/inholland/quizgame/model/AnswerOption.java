package nl.inholland.quizgame.model;

public class AnswerOption {
    private final String text;
    private final Object value;

    public AnswerOption(String text, Object value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public Object getValue() {
        return value;
    }
}
