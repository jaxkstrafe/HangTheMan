import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        
        HangTheMan hangTheMan = new HangTheMan();
        WordDisplay wordDisplay = new WordDisplay();
        BorderPane bPane = new BorderPane();

        bPane.setLeft(hangTheMan);
        bPane.setBottom(wordDisplay);

        Scene scene = new Scene(bPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hangman Game");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
