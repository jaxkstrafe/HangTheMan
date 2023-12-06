// This class would handle drawing the hangman as the game progress
// If a player gets a guess wrong then the HangTheMan class should be called and should update
// the 'score' or add a body part to the man
//

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HangTheMan extends Pane {

    private Canvas canvas;
    double width = 500;
    double height = 500;

    public HangTheMan() {
        canvas = new Canvas(width, height);
        getChildren().add(canvas);
        drawHangman(); 
    }

    private void drawHangman() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0); 


        // Draw the hangman figure 
        gc.strokeLine(100, 500, 400, 500); // Base line
        gc.strokeLine(250, 500, 250, 100); // Vertical pole
        gc.strokeLine(250, 100, 350, 100); // Upper horizontal pole
        gc.strokeLine(350, 100, 350, 160); // Rope
    }

    // Method to update the hangman figure based on incorrect guesses
    public void updateHangman(int incorrectGuessCount) {
        // We could honestly just draw a couple images and assign each image a number which
        // would correlate to how many wrong guesses the player has gotten
    }
}

