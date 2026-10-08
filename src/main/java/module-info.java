module nl.inholland.quizgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens nl.inholland.quizgame to javafx.fxml;
    exports nl.inholland.quizgame;
}