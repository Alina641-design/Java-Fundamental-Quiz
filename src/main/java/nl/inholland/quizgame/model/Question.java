package nl.inholland.quizgame.model;

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
}
