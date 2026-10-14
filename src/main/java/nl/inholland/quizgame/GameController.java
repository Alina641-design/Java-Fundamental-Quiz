package nl.inholland.quizgame;
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
import nl.inholland.quizgame.model.BooleanQuestion;
import nl.inholland.quizgame.model.MultipleChoiceQuestion;
import nl.inholland.quizgame.model.Question;
import nl.inholland.quizgame.model.Quiz;

import java.util.Collections;
import java.util.List;

public class GameController {
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label questionTitleLabel;
    @FXML
    private VBox answerBox;
    private final ToggleGroup answerGroup = new ToggleGroup();
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
    public void setQuiz(Quiz quiz) {
        Question question = quiz.getPages().get(0).getQuestion();
        questionTitleLabel.setText(question.getTitle());
        answerBox.getChildren().clear();
        answerGroup.getToggles().clear();
        if (question instanceof MultipleChoiceQuestion) {
            showMulipleChoice((MultipleChoiceQuestion) question);
        }else if (question instanceof BooleanQuestion) {
            showBoolean((BooleanQuestion) question);
        }
    }
    private void showMulipleChoice(MultipleChoiceQuestion question) {
        List<String> choices = question.getChoices();
        if ("random".equals(question.getChoicesOrder())) {
            Collections.shuffle(choices);
        }
        for (String choice : choices) {
            addAnswer(choice, choice);
        }
    }
    private void showBoolean(BooleanQuestion question) {
        addAnswer(question.getLabelTrue(), true);
        addAnswer(question.getLabelFalse(), false);
    }
    private void addAnswer(String text, Object value) {
        RadioButton button = new RadioButton(text);
        button.setWrapText(true);
        button.setToggleGroup(answerGroup);
        button.setUserData(value);
        answerBox.getChildren().add(button);
    }
}
