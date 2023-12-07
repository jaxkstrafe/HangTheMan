public class ChooseWord {
    
    String[] words = {"cake", "bird", "tree", "play", "frog", "book", "rain", "fish", 
    "game", "ship", "apple", "beach", "chair", "dance", "eagle", "fancy", "glass", 
    "happy", "igloo", "jelly", "banana", "camera", "dancer", "eleven", "falcon", 
    "guitar", "hammer", "jacket", "killer", "laptop"};

    public String randomWord(){

        String randomWord = words[(int) Math.floor(Math.random() * words.length)];

        String word = randomWord;

        return word;
    }

}
