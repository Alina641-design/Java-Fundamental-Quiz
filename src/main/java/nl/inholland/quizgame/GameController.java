package nl.inholland.quizgame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;

public class GameController {
    @FXML
    private Label playerNameLabel;
    public void setPlayerName(String playerName) {
        playerNameLabel.setText("Player: " + playerName);
    }
    @FXML
    private void onFinishQuizClick(){
        try {
            URL location = getClass().getResource("/nl/inholland/quizgame/results-view.fxml");
            if (location == null) {
                throw new IOException("Results screen is not found.");
            }
            Parent root = FXMLLoader.load(location);
            playerNameLabel.getScene().setRoot(root);
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't open the Results");
            alert.initOwner(playerNameLabel.getScene().getWindow());
            alert.showAndWait();
        }
    }
}
