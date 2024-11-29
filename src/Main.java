import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        double startTime = System.currentTimeMillis();

        //lecture du fichier fautes
        Mistakes mistakes = new Mistakes();
        FileReader.readFile("src/fautes.txt",mistakes);

        //creation du dictionnaire
        Dico dictionnaire = new Dico();
        FileReader.readFile("src/dictionnaire.txt",dictionnaire);
        HashSet<String> dictionnaireWords = dictionnaire.getDicoWords();
        Trigram dictionnaireTrigrams = new Trigram();
        dictionnaireTrigrams.fillTrigramAndWords(dictionnaireWords);

        //creation du correcteur
        WordCorrector corrector = new WordCorrector(dictionnaireTrigrams, dictionnaire);
        corrector.correctFile(mistakes);


        double endTime = System.currentTimeMillis();
        double duration = endTime - startTime;
        System.out.println(duration/1000 +" secondes ");
    }

}