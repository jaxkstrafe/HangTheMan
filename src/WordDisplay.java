// This class would handle displaying the word that the player is guessing
// For example how many letters there might be or what letters to display based on the players guesses

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class WordDisplay extends HBox {
    String[] words = {"cake", "bird", "tree", "play", "frog", "book", "rain", "fish", 
    "game", "ship", "apple", "beach", "chair", "dance", "eagle", "fancy", "glass", 
    "happy", "igloo", "jelly", "banana", "camera", "dancer", "eleven", "falcon", 
    "guitar", "hammer", "jacket", "killer", "laptop"};

    String wordToGuess;
    Rectangle blank;


    public WordDisplay() {
        this.setSpacing(5);
        this.setPadding(new Insets(20));

        for (char c = 'A'; c <= 'Z'; c++) {
            Button letterButton = new Button(String.valueOf(c));
            letterButton.setPrefWidth(40);
            letterButton.setPrefHeight(40);
            this.getChildren().add(letterButton);
        }

        
    }

    public String getRandomWord(){
        String randomWord = words[(int) Math.floor(Math.random() * words.length)];
        String word = randomWord;
        return word;
    }
}
