module nl.inholland.quizgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens nl.inholland.quizgame to javafx.fxml;
    exports nl.inholland.quizgame;
}