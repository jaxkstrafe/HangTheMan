import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        GamePane gamePane = new GamePane();
        Scene scene = new Scene(gamePane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hangman Game");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
