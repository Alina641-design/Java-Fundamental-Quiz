package nl.inholland.quizgame.service;

public class GameManager {
    private static final GameManager instance = new GameManager();
    private GameManager() {

    }
    public static GameManager getInstance() {
        return instance;
    }
}
