import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Trigram {

    private HashMap<String, HashSet<String>> trigramAndWords = new HashMap<>();

    public Trigram(){}

    public static ArrayList<String> generateTrigramsOf(String word){
        ArrayList<String> wordTrigram = new ArrayList<>();
        String newWord = "<"+word+">";
        for(int i = 0; i <= newWord.length()-3; i++){
            wordTrigram.add(newWord.substring(i,i+3));
        }
        return wordTrigram;
    }

    public void fillTrigramAndWords(HashSet<String> words){
        ArrayList<String> wordTrigram = null;
        for(String word : words){
            wordTrigram = generateTrigramsOf(word);
            for(String trigram : wordTrigram){
                if(!trigramAndWords.containsKey(trigram)){
                    trigramAndWords.put(trigram,new HashSet<>());
                }
                trigramAndWords.get(trigram).add(word);
            }
        }
    }

    public HashMap<String, HashSet<String>> getTrigramAndWords() {
        return trigramAndWords;
    }

    public boolean containsTrigram(String trigram){
        return trigramAndWords.containsKey(trigram);
    }

    public void print(){
        for(String trigram : trigramAndWords.keySet()){
            System.out.println("trigramme : "+ trigram + " liste :"+ trigramAndWords.get(trigram));
            System.out.println();
            System.out.println();
        }
    }
}

