import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GamePane extends Pane {
    static String genre = "";
    String[] words = {
        /*"cake", "bird", "tree", "play", "frog", "book", "rain", "fish",
        "game", "ship", "apple", "beach", "chair", "dance", "eagle", "fancy",
        "glass", "happy", "igloo", "jelly", "banana", "dancer", "eleven", 
        "falcon", "guitar", "hammer", "jacket", "killer", "laptop", "zebra", 
        "garden", "moon", "robot", "sunset", "dragon", "island", "ninja",
        "octopus", "parrot", "quasar", "violet", "whale", "yacht", "zephyr",
        "acoustic", "blossom", "cascade", "eclipse", "flamingo", "galaxy",
        "lullaby", "monsoon", "candy", "blaze", "apple", "ghost", "happy", 
        "magic", "music", "ocean", "piano", "queen", "river", "smile", "train", 
        "umbra", "zebra", "fairy", "jelly", "lucky", "noble", "snail"*/
    };
    String wordToGuess = "";
    Rectangle wordToGuessWhiteBackground;
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
    private ImageView backgroundImage;
    private Label incorrectGuessLabel;

    public GamePane() {
        if (genre == "SFA") {words = WordList.sfa;}
        else if (genre.equals("Computer Science")) {words = WordList.compSci;}
        else if (genre.equals("Animals")) {words = WordList.animals;}
        else if (genre.equals("Miscellaneous")) {words = WordList.misc;}
        else {words = WordList.all;}
        spacing = 1.05;
        wordToGuess = getRandomWord(words);
        letterButtonsOfWordToGuess = new ArrayList<>();
        letterButtonsOfAlphabet = new ArrayList<>();
        initializeBackgroundImages();
        initializeGallowsImage();
        initializeHangmanImage();
        intializeIncorrectCounter();
        
        drawLetterButtons();
        incorrectGuessCount = 0;

        createWordToGuess();
        
    }

    public void createWordToGuess(){
        for (int i = 0; i < wordToGuess.length(); i++) {
            LetterButton letterButton = new LetterButton(0, wordToGuessY, Character.MIN_VALUE);
            letterButton.setTranslateX(i * (LetterButton.getWidthOfObject() * spacing) + wordToGuessX);
            letterButton.setTranslateY(wordToGuessY);
            letterButton.setDisable(true);
    
            // Increase the font size for the word to guess
            letterButton.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-font-size: 15px; -fx-font-weight: bold; -fx-underline: true;");
    
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
    private void initializeBackgroundImages() {
        backgroundImage = new ImageView();
        Image img = new Image("images//background.jpg");
        backgroundImage.setImage(img);
        backgroundImage.setTranslateX(-400);
        backgroundImage.setTranslateY(-300);
        backgroundImage.setScaleX(0.75);
        backgroundImage.setScaleY(0.75);
        
        wordToGuessWhiteBackground = new Rectangle(wordToGuessX, wordToGuessY, wordToGuess.length() * (LetterButton.getWidthOfObject() * spacing), 50);
        updateBackgroundImage();
        wordToGuessWhiteBackground.setFill(Color.WHITE);
        getChildren().addAll(backgroundImage, wordToGuessWhiteBackground); 
    }
    private void intializeIncorrectCounter() {
        incorrectGuessLabel = new Label("Total Incorrect Guesses: " + incorrectGuessCount);
        incorrectGuessLabel.setTextFill(Color.WHITE);
        BorderPane bPane = new BorderPane(incorrectGuessLabel);
        getChildren().add(bPane);
        bPane.setViewOrder(-2);
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
    private void updateBackgroundImage() {
        wordToGuessWhiteBackground.setWidth(wordToGuess.length() * (LetterButton.getWidthOfObject() * spacing));
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
            showEndGamePopup("Congratulations! You win! The word was: " + wordToGuess);
        }
    }

    public void handleWrongGuess() {
        incorrectGuessCount++;
        updateIncorrectGuessLabel();
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
        updateBackgroundImage();
        createWordToGuess();
        updateIncorrectGuessLabel();


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
            letterButton.setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-font-size: 15px; -fx-font-weight: bold; -fx-underline: true;");


            getChildren().add(letterButton);
            letterButtonsOfWordToGuess.add(letterButton);
        }
    }
    private void updateIncorrectGuessLabel(){
        incorrectGuessLabel.setText("Total Incorrect Guesses: " + incorrectGuessCount);
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
