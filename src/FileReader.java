import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    public static void readFile(String url, WordContainer wordContainer){
        try{
            File file = new File(url);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String word = scanner.nextLine();
                wordContainer.addWord(word);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("fichier à l'url "+ url+ " introuvable");
        }
    }
}
