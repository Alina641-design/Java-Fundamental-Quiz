package nl.inholland.quizgame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.Label;
import nl.inholland.quizgame.service.GameManager;

public class ResultsController {
    @FXML
    private Button menuButton;
    @FXML
    private Label playerNameLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private void initialize() {
        GameManager manager = GameManager.getInstance();
        playerNameLabel.setText("Player: " + manager.getPlayerName());
        scoreLabel.setText("Score: " + manager.getScore());
    }
    @FXML
    private void onBackToMenuClick() {
        try {
            URL location = getClass().getResource("/nl/inholland/quizgame/menu-view.fxml");
            if (location == null) {
                throw new IOException("Menu screen not found.");
            }
            Parent root = FXMLLoader.load(location);
            menuButton.getScene().setRoot(root);
        }catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't open the Menu");
            alert.initOwner(menuButton.getScene().getWindow());
            alert.showAndWait();
        }
        }
    }

