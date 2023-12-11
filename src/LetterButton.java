import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class LetterButton extends Button {
    private static double width = 42;
    private static double height = 50;
    private char letter;
    private boolean guessed;
    public LetterButton(double x, double y, char letter) {
        guessed = false;
        
        this.setTranslateX(this.getTranslateX() + x);
        this.setTranslateY(this.getTranslateY() + y);
        setPrefWidth(width);
        setPrefHeight(height);
        setText(String.valueOf(letter));
        setStyle("-fx-font-weight: bold");

        setOnAction(e -> {
            setDisable(true); 
            if (!guessed)
                guessed = true; 
        });
        setTextFill(Color.BLACK);
    }
    public static double getWidthOfObject() {
        return width;
    }
    public char getLetter() {
        return this.letter;
    }
    public void setLetter(char letter) {
        setText(String.valueOf(letter));
    }
}