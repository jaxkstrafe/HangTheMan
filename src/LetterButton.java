import javafx.scene.control.Button;

public class LetterButton extends Button {
    private static double width;
    private static double height;
    private char letter;
    public LetterButton(double x, double y, char letter) {
        width = 35;
        height = 50;
        
        this.setTranslateX(this.getTranslateX() + x);
        this.setTranslateY(this.getTranslateY() + y);
        setPrefWidth(width);
        setPrefHeight(height);
        setText(String.valueOf(letter));
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