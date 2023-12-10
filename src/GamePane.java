import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


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
    int incorrectGuessCount;
    int correctGuessCount;
    private ImageView hangmanImage;

    public GamePane() {
        spacing = 1.05;
        wordToGuess = getRandomWord(words);
        letterButtonsOfWordToGuess = new ArrayList<>();
        drawLetterButtons();
        incorrectGuessCount = 0;
        initializeHangmanImage();

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

    private void initializeHangmanImage() {
        this.hangmanImage = new ImageView(); 
        this.hangmanImage.setFitWidth(350);
        this.hangmanImage.setFitHeight(350);
        this.hangmanImage.setTranslateX(50);
        this.hangmanImage.setTranslateY(50);
        updateHangmanImage(); 
        getChildren().add(this.hangmanImage); 
    }

    private void updateHangmanImage() {
        Image image;
        if (incorrectGuessCount <= 6)
            image = new Image(incorrectGuessCount + ".png");
        else
            image = new Image("6.png");
        hangmanImage.setImage(image);
    }

    // This method keeps track of the incorrect guesses and once it reaches 6 the player loses
    public void handleWrongGuess() {
        incorrectGuessCount++;
        System.out.println("Total wrong guesses: " + incorrectGuessCount); 

        updateHangmanImage();

        if(incorrectGuessCount >= 6){
            endGame("You Lose! The word was " + wordToGuess);
        }
    }

    public void handleCorrectGuess() {
        correctGuessCount++; 

        if (correctGuessCount >= wordToGuess.length()) {
            endGame("Congratulations! You win!"); 
            // in this method we could change the endGame class to display a popup that asks
            // the user if they want to play again or quit
        }
    }

    private void endGame(String message) {
        System.out.println(message); // Print the end message to the console
        // We could do some sort of pop up asking the player wheather they want to quit or restart after they win or lose
    }

    public void guessLetter(char letter) {
        boolean guess = false;
        for (int i = 0; i < letterButtonsOfWordToGuess.size(); i++) {
            for (int j = 0; j < wordToGuess.length(); j++) {
                if (wordToGuess.charAt(j) == Character.toLowerCase(letter)) {
                    letterButtonsOfWordToGuess.get(j).setLetter(letter);
                    guess = true;
                }
            }
        }

        if(guess){
            handleCorrectGuess();
        }
        
        if (!guess) {
            handleWrongGuess();
        }
    }
}
