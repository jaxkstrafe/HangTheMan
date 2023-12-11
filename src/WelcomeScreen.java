import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeScreen {
    public static Scene createWelcomeScene(Stage primaryStage) {
        Pane welcomeScreen = new Pane();
        Scene welcomeScene = new Scene(welcomeScreen, 400, 300);

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startGame(primaryStage));

        double buttonWidth = 100; 
        double buttonHeight = 50; 
        startButton.setLayoutX((welcomeScene.getWidth() - buttonWidth) / 2);
        startButton.setLayoutY((welcomeScene.getHeight() - buttonHeight) / 2);
        startButton.setPrefSize(buttonWidth, buttonHeight);

        welcomeScreen.getChildren().add(startButton);

        return welcomeScene;
    }

    private static void startGame(Stage primaryStage) {
        GamePane gamePane = new GamePane();
        Scene gameScene = new Scene(gamePane, 1000, 600);

        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Hangman Game");
        primaryStage.show();
    }
}
