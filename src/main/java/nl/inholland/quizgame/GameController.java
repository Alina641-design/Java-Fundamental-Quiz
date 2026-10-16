package nl.inholland.quizgame;
import nl.inholland.quizgame.service.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import nl.inholland.quizgame.model.Question;
import javafx.scene.control.Button;
import nl.inholland.quizgame.model.AnswerOption;

public class GameController {
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label questionTitleLabel;
    @FXML
    private VBox answerBox;
    private final ToggleGroup answerGroup = new ToggleGroup();
    @FXML
    private Label scoreLabel;
    @FXML
    private Button submitAnswerButton;
    @FXML
    private void initialize() {
        GameManager manager = GameManager.getInstance();
        playerNameLabel.setText("Player: " + manager.getPlayerName());
        scoreLabel.textProperty().bind(manager.scoreProperty().asString("Score: %d"));
        showCurrentQuestion();
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
    private void showCurrentQuestion() {
        Question question = GameManager.getInstance().getCurrentQuestion();
        questionTitleLabel.setText(question.getTitle());
        answerBox.getChildren().clear();
        answerGroup.getToggles().clear();
        answerBox.setDisable(false);
        submitAnswerButton.setDisable(false);
        for (AnswerOption option : question.getAnswerOptions()) {
            addAnswer(option.getText(), option.getValue());
        }
    }

    private void addAnswer(String text, Object value) {
        RadioButton button = new RadioButton(text);
        button.setWrapText(true);
        button.setToggleGroup(answerGroup);
        button.setUserData(value);
        answerBox.getChildren().add(button);
    }
    @FXML
    private void onSubmitAnswerClick() {
        Object answer = getSelectedAnswer();
        GameManager manager = GameManager.getInstance();
        if (answer == null && manager.getCurrentQuestion().isRequired()) {
            showAnswerWarning();
            return;
        }
        manager.submitAnswer(answer);
        answerBox.setDisable(true);
        submitAnswerButton.setDisable(true);
    }

    private Object getSelectedAnswer() {
        if (answerGroup.getSelectedToggle() == null) {
            return null;
        }
        return answerGroup.getSelectedToggle().getUserData();
    }

    private void showAnswerWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Select an answer first.");
        alert.initOwner(answerBox.getScene().getWindow());
        alert.showAndWait();
    }
}
