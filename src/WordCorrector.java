import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

public class WordCorrector {

    private Trigram trigram;
    private Dico dictionnaire;


    public WordCorrector(Trigram trigram, Dico dictionnaire){
        this.trigram = trigram;
        this.dictionnaire = dictionnaire;
    }

    public ArrayList<String> wordsWithCommonTrigramWith(String word){
        ArrayList<String> wordTrigrams = Trigram.generateTrigramsOf(word);
        ArrayList<String> wordsWithCommonTrigram = new ArrayList<>();
        for(String trg : wordTrigrams){
            if(trigram.containsTrigram(trg))
                wordsWithCommonTrigram.addAll(trigram.getTrigramAndWords().get(trg));
        }
        return wordsWithCommonTrigram;
    }

    public HashSet<String> wordsHavingTheMostTrigramsInCommonWith(ArrayList<String> wordsWithCommonTrg){
        HashMap<String,Integer> wordAndOccurrence = wordsAndOccurrences(wordsWithCommonTrg);
        HashSet<String> hundredWordsSelected = new HashSet<>(100);
        for(String word : wordAndOccurrence.keySet()){
            if(hundredWordsSelected.size() < 100){
                hundredWordsSelected.add(word);
            }
            else{
                String wordMinOcc = findWordWithMinOcc(hundredWordsSelected,wordAndOccurrence);
                if(wordAndOccurrence.get(word) > wordAndOccurrence.get(wordMinOcc)){
                    hundredWordsSelected.remove(wordMinOcc);
                    hundredWordsSelected.add(word);
                }
            }
        }
        return hundredWordsSelected;
    }

    public String findWordWithMinOcc(HashSet<String> words, HashMap<String,Integer> pairs){
        int minOcc = 10000000;
        String wordMinOcc = null;
        for(String word : words){
            if(pairs.get(word) < minOcc){
                minOcc = pairs.get(word);
                wordMinOcc = word;
            }
        }
        return wordMinOcc;
    }

    public HashMap<String,Integer> wordsAndOccurrences(ArrayList<String> list){
        HashMap<String,Integer> wordAndOccurrence = new HashMap<>();
        for(String word : list){
            wordAndOccurrence.put(word,wordAndOccurrence.getOrDefault(word,1)+1);
        }
        return wordAndOccurrence;
    }

    // TODO: 16/11/2023 continuer la derniere partie du tp

    public HashSet<String> correctWord(String word){
        if(!dictionnaire.containsWord(word)){
            ArrayList<String> CommonTrg = this.wordsWithCommonTrigramWith(word);
            HashSet<String> mostCommonTrg = this.wordsHavingTheMostTrigramsInCommonWith(CommonTrg);
            HashSet<String> wordCorrections = new HashSet<>(5);
            HashMap<String,Integer> wordsAndDist = this.wordsAndDistances(mostCommonTrg,word);
            for(String wrd : mostCommonTrg){
                if(wordCorrections.size() < 5){
                    wordCorrections.add(wrd);
                }
                else{
                    String wordMaxDist = this.findWordWithMaxDistance(wordCorrections,wordsAndDist);
                    if(wordsAndDist.get(wrd) < wordsAndDist.get(wordMaxDist)){
                        wordCorrections.remove(wordMaxDist);
                        wordCorrections.add(wrd);
                    }
                }
            }
            return wordCorrections;
        }
        System.out.println("le mot "+ word +" ne contient pas d'erreur");
        System.out.println();
        System.out.println();
        return null;
    }

    public HashMap<String,Integer> wordsAndDistances(HashSet<String> mostCommonTrg, String word){
        HashMap<String,Integer> wordAndDistance = new HashMap<>();
        for(String wrd : mostCommonTrg){
            wordAndDistance.put(wrd,WordDistanceCalculator.calculateDistance(wrd,word));
        }
        return wordAndDistance;
    }

    public String findWordWithMaxDistance(HashSet<String> wordCorrections, HashMap<String,Integer> wordAndDistance){
        int maxDistance = 0;
        String wordMaxDistance = null;
        for(String word : wordCorrections){
            if(wordAndDistance.get(word) > maxDistance){
                maxDistance = wordAndDistance.get(word);
                wordMaxDistance = word;
            }
        }
        return wordMaxDistance;
    }

    public void printCorrection(String word, HashSet<String> corrections){
        if(corrections!= null){
            System.out.println(word);
            for(String wrd : corrections){
                System.out.println("    "+ wrd);
            }
            System.out.println();
            System.out.println();
        }
    }

    public void correctFile(Mistakes mistakes){
        ArrayList<String> listOfMistakes = mistakes.getMistakesWords();
        for(String mistake : listOfMistakes){
            printCorrection( mistake , this.correctWord(mistake));
        }
    }


}
