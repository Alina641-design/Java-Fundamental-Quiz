package nl.inholland.quizgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import nl.inholland.quizgame.model.Quiz;
import nl.inholland.quizgame.service.QuizLoader;
import nl.inholland.quizgame.service.GameManager;

import java.io.File;


import java.io.IOException;

public class MenuController {
    private static final int MAX_NAME_LENGTH = 50;
    @FXML
    private Button startButton;
    private final QuizLoader quizLoader = new QuizLoader();
    private Quiz loadedQuiz;
    @FXML
    private Label statusLabel;

    @FXML
    private void onStartQuizClick() {
        if (loadedQuiz == null) {
            return;
        }
        String playerName = askPlayerName();
        if (playerName == null) {
            return;
        }
        playerName = playerName.strip();
        if (isValidName(playerName)) {
            openGame(playerName);
        }
    }

    private String askPlayerName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.initOwner(statusLabel.getScene().getWindow());
        dialog.setTitle("Start Quiz");
        dialog.setHeaderText("Enter your name:");
        return dialog.showAndWait().orElse(null);
    }

    private boolean isValidName(String playerName) {
        if (playerName.isEmpty() || playerName.length() > MAX_NAME_LENGTH) {
            statusLabel.setText("Enter a name between 1 and " + MAX_NAME_LENGTH + " characters.");
            return false;
        }
        return true;
    }

    private void openGame(String playerName) {
        try {
            GameManager manager = GameManager.getInstance();
            manager.setPlayerName(playerName);
            manager.setQuiz(loadedQuiz);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            Parent root = loader.load();
            statusLabel.getScene().setRoot(root);
        } catch (IOException e) {
            statusLabel.setText("Error occurred while opening the game.");
        }
    }

    @FXML
    private void onLoadQuizClick() {
        File file = chooseQuizFile();
        if (file == null) {
            return;
        }
        loadQuizFile(file);
    }

    private File chooseQuizFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a Quiz file");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        return chooser.showOpenDialog(statusLabel.getScene().getWindow());
    }

    private void loadQuizFile(File file) {
        try {
            loadedQuiz = quizLoader.loadQuiz(file);
            startButton.setDisable(false);
            statusLabel.setText("Loaded: " + loadedQuiz.getTitle() + "\nUnsupported quiestions skipped: " + quizLoader.getSkippedQuestions());
        } catch (IOException e) {
            loadedQuiz = null;
            startButton.setDisable(true);
            statusLabel.setText(e.getMessage());
        }

    }
}