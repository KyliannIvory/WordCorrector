import java.util.HashMap;
import java.util.HashSet;

public class Dico implements WordContainer{

    private HashSet<String> dicoWords = new HashSet<>();

    @Override
    public void addWord(String word) {
        dicoWords.add(word);
    }

    public HashSet<String> getDicoWords() {
        return dicoWords;
    }

    public boolean containsWord(String word){
        return dicoWords.contains(word);
    }
}
