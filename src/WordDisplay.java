// This class would handle displaying the word that the player is guessing
// For example how many letters there might be or what letters to display based on the players guesses

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class WordDisplay extends HBox {


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

}
