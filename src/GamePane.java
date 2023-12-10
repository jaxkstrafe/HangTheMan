import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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
    ArrayList<LetterButton> letterButtonsOfAlphabet;
    int incorrectGuessCount;
    int correctGuessCount;
    private ImageView hangmanImage;
    private ImageView gallowsImage;

    public GamePane() {
        spacing = 1.05;
        wordToGuess = getRandomWord(words);
        letterButtonsOfWordToGuess = new ArrayList<>();
        letterButtonsOfAlphabet = new ArrayList<>();
        initializeGallowsImage();
        initializeHangmanImage();
        drawLetterButtons();
        incorrectGuessCount = 0;

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
            letterButtonsOfAlphabet.add(letterButton);
        }
    }

    public String getRandomWord(String[] stringArray) {
        String word = words[(int) Math.floor(Math.random() * stringArray.length)];
        System.out.println("The word: " + word);
        return word;
    }

    private void initializeHangmanImage() {
        hangmanImage = new ImageView(); 
        hangmanImage.setFitWidth(350);
        hangmanImage.setFitHeight(350);
        hangmanImage.setTranslateX(60);
        hangmanImage.setTranslateY(180);
        hangmanImage.setScaleX(0.85);
        hangmanImage.setScaleY(0.85);
        updateHangmanImage(); 
        getChildren().add(this.hangmanImage); 
    }
    private void initializeGallowsImage() {
        gallowsImage = new ImageView();
        gallowsImage.setTranslateX(-200);
        gallowsImage.setTranslateY(100);
        gallowsImage.setScaleX(1.5);
        gallowsImage.setScaleY(1.5);
        updateGallowsImage();
        getChildren().add(gallowsImage); 
    }

    private void updateHangmanImage() {
        Image image;
        if (incorrectGuessCount <= 6)
            image = new Image("images//shrek-" + incorrectGuessCount + ".png");
        else
            image = new Image("images//shrek-6.png");
        hangmanImage.setImage(image);
    }
    private void updateGallowsImage() {
        Image image;
        if (incorrectGuessCount <= 5)
            image = new Image("images//gallows-closed.png");
        else
            image = new Image("images//gallows-open.png");
        gallowsImage.setImage(image);
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
            handleCorrectGuess(letter);
        }
        if (!guess) {
            handleWrongGuess();
        }
    }

    public void handleCorrectGuess(char guessedLetter) {
        int occurrences = countOccurrences(guessedLetter);
        correctGuessCount += occurrences;

        if (correctGuessCount == wordToGuess.length()) {
            showEndGamePopup("Congratulations! You win!");
        }
    }

    public void handleWrongGuess() {
        incorrectGuessCount++;
        System.out.println("Total wrong guesses: " + incorrectGuessCount); 

        updateGallowsImage();
        updateHangmanImage();
        

        if(incorrectGuessCount >= 6){
            showEndGamePopup("You Lose! The word was " + wordToGuess);
        }
    }

    // This method handles how many occureneces of a letter there are in the word the player is guessing
    // and update the count accordingly. For example if the word is glass and the player chooses s
    // the count will be updated to 2 as there are 2 s's in the word glass
    private int countOccurrences(char guessedLetter) {
        int count = 0;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == Character.toLowerCase(guessedLetter)) {
                count++;
            }
        }
        return count;
    }



    private void restartGame() {
        wordToGuess = getRandomWord(words);
        incorrectGuessCount = 0;
        correctGuessCount = 0;
        updateHangmanImage();
        updateDisplayedWord();

        // Re enables any letter buttons that were disabled
        for (LetterButton letterButton : letterButtonsOfAlphabet) {
            letterButton.setDisable(false); // Enable all alphabet buttons for guessing
        }
    }

    // When the game is restarted this method will be called and resets the letterButtonsOfWordToGuess
    private void updateDisplayedWord() {
        getChildren().removeAll(letterButtonsOfWordToGuess); // Remove previous letter buttons

        // Clear the list and add new LetterButton objects based on the length of the wordToGuess
        letterButtonsOfWordToGuess.clear();
        double startX = wordToGuessX;

        for (int i = 0; i < wordToGuess.length(); i++) {
            LetterButton letterButton = new LetterButton(0, wordToGuessY, Character.MIN_VALUE);
            letterButton.setTranslateX(i * (LetterButton.getWidthOfObject() * spacing) + startX);
            letterButton.setTranslateY(wordToGuessY);
            letterButton.setDisable(true);

            getChildren().add(letterButton);
            letterButtonsOfWordToGuess.add(letterButton);
        }
    }

    // When the game is over the player will be shown this popup which will give them the option to 
    // either quit and close the game or restart the game
    private void showEndGamePopup(String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(message);
        alert.setContentText("What would you like to do?");

        ButtonType restartButton = new ButtonType("Restart");
        ButtonType quitButton = new ButtonType("Quit");
        alert.getButtonTypes().setAll(restartButton, quitButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == restartButton) {
                restartGame();
            } else if (result.get() == quitButton) {
                Stage stage = (Stage) this.getScene().getWindow();
                stage.close();
            }
        }
    }
}
