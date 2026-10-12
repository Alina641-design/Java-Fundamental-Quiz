package nl.inholland.quizgame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;

public class MenuController {
    private static final int MAX_NAME_LENGTH = 50;
    @FXML
    private Label statusLabel;

    @FXML
    private void onStartQuizClick() {
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
            statusLabel.setText("Enter a name betwen and " + MAX_NAME_LENGTH + " characters.");
            return false;
        }
        return true;
    }
    private void openGame(String playerName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            Parent root = loader.load();
            GameController controller = loader.getController();
            controller.setPlayerName(playerName);
            statusLabel.getScene().setRoot(root);
        }catch (IOException e) {
            statusLabel.setText("Error occurred while opening the game.");
        }
        }
    }