import java.util.ArrayList;

public class Mistakes implements WordContainer{

    ArrayList<String> mistakesWords = new ArrayList<>();
    @Override
    public void addWord(String word) {
        mistakesWords.add(word);
    }

    public ArrayList<String> getMistakesWords() {
        return mistakesWords;
    }
}
