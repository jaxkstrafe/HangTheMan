import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePane extends Pane {
    String[] words = { "cake", "bird", "tree", "play", "frog", "book", "rain", "fish",
            "game", "ship", "apple", "beach", "chair", "dance", "eagle", "fancy", "glass",
            "happy", "igloo", "jelly", "banana", "camera", "dancer", "eleven", "falcon",
            "guitar", "hammer", "jacket", "killer", "laptop" };
    String wordToGuess = "";
    double wordToGuessX = 500;
    double wordToGuessY = 200;
    LetterButton letterButton;
    Pane paneLetterButtons;
    double spacing;
    ArrayList<LetterButton> letterButtonsOfWordToGuess;

    public GamePane() {
        spacing = 1.05;
        wordToGuess = getRandomWord(words);
        letterButtonsOfWordToGuess = new ArrayList<>();
        drawExecutionDevice();
        drawLetterButtons();

        for (int i = 0; i < wordToGuess.length(); i++) {
            letterButton = new LetterButton(0, wordToGuessY, Character.MIN_VALUE);
            letterButton.setTranslateX(i * (LetterButton.getWidthOfObject() * spacing) + wordToGuessX);
            letterButton.setTranslateY(wordToGuessY);
            letterButton.setDisable(true);

            getChildren().add(letterButton);
            letterButtonsOfWordToGuess.add(letterButton);
        }
    }

    public void drawLetterButtons() {
        paneLetterButtons = new Pane();
        for (char c = 'A'; c <= 'Z'; c++) {
            letterButton = new LetterButton(((int) c - (int)'A') * (LetterButton.getWidthOfObject() * spacing), 500, c);
            char currentCharacter = c;
            letterButton.setOnMouseClicked(e -> guessLetter(currentCharacter));
            getChildren().add(letterButton);
        }
    }

    public String getRandomWord(String[] stringArray) {
        String word = words[(int) Math.floor(Math.random() * stringArray.length)];
        System.out.println("The word: " + word);
        return word;
    }

    private void drawExecutionDevice() {
        /*
         * GraphicsContext gc = canvas.getGraphicsContext2D();
         * gc.setStroke(Color.BLACK);
         * gc.setLineWidth(2.0);
         * 
         * // Draw the hangman figure
         * gc.strokeLine(100, 500, 400, 500); // Base line
         * gc.strokeLine(250, 500, 250, 100); // Vertical pole
         * gc.strokeLine(250, 100, 350, 100); // Upper horizontal pole
         * gc.strokeLine(350, 100, 350, 160); // Rope
         */
    }

    public void updateHangman(int incorrectGuessCount) {
        // We could honestly just draw a couple images and assign each image a number
        // which
        // would correlate to how many wrong guesses the player has gotten
    }

    public void guessLetter(char letter) {
        for (int i = 0; i < letterButtonsOfWordToGuess.size(); i++) {
            for (int j = 0; j < wordToGuess.length(); j++) {
                if (wordToGuess.charAt(j) == Character.toLowerCase(letter))
                    letterButtonsOfWordToGuess.get(j).setLetter(letter);
            }
        }
    }
}
