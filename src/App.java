import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene welcomeScene = WelcomeScreen.createWelcomeScene(primaryStage);

        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Hangman Game - Welcome");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
