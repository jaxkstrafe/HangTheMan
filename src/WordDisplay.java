// This class would handle displaying the word that the player is guessing
// For example how many letters there might be or what letters to display based on the players guesses

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class WordDisplay extends HBox {
    String[] words = { "cake", "bird", "tree", "play", "frog", "book", "rain", "fish",
            "game", "ship", "apple", "beach", "chair", "dance", "eagle", "fancy", "glass",
            "happy", "igloo", "jelly", "banana", "camera", "dancer", "eleven", "falcon",
            "guitar", "hammer", "jacket", "killer", "laptop" };

    String randomWord;
    Rectangle blank;
    int letterSize = 40;

    public WordDisplay() {
        this.setSpacing(5);
        this.setPadding(new Insets(20));
        randomWord = getRandomWord();

        for (char c = 'A'; c <= 'Z'; c++) {
            Button letterButton = new Button(String.valueOf(c));
            letterButton.setPrefWidth(letterSize);
            letterButton.setPrefHeight(letterSize);
            letterButton.setOnMouseClicked(e -> guessLetter(letterButton.getText()));
            this.getChildren().add(letterButton);
        }

        for (int i = 0; i < randomWord.length(); i++) {
            blank = new Rectangle(i, 0, 50, 5);
            blank.setTranslateX(-300);
            blank.setTranslateY(-300);
            this.getChildren().add(blank);
        }
    }

    public String getRandomWord() {
        String randomWord = words[(int) Math.floor(Math.random() * words.length)];
        String word = randomWord;
        return word;
    }

    public void guessLetter(String letter) {
        System.out.println(letter);
    }
}
