
/*
 * Proyecto 2 Parte 1
 * 
 * Garcia Ibañez Luis Arturo
 * 
 * 4CM11
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpellChecker {
    final String booksFolder = "LIBROS_TXT";
    public Map<String, Boolean> dictionary;
    public ArrayList<File> source;

    public String CheckException(String word) {
        word = word.replaceAll("[0-9]|[\\.]|-|_|,|/|\\*|\\^|\\(|\\)|;|:|\\$|%|\\?|¿|¡|\\!|—", "");
        return word;
    }

    private ArrayList<File> getBooks() {
        File folder = new File(booksFolder);
        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));
        for (File file : listFiles) {
            if (file.isDirectory()) {
                listFiles.remove(file);
            }
        }
        return listFiles;
    }

    private void generateKnowledgeBase() {
        System.out.println("Generating Knowledge Base");
        for (File file : source) {
            try {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    for (String word : line.split(" ")) {
                        String cleanedWord = CheckException(word);
                        if (!cleanedWord.equals("") && !dictionary.containsKey(cleanedWord)) {
                            dictionary.put(cleanedWord, true);
                        } else {
                            continue;
                        }
                    }
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ready! You can type any word or sentence");
    }

    public Boolean checkWord(String word) {
        return dictionary.containsKey(word);
    }

    public SpellChecker() {
        dictionary = new HashMap<>();
        source = getBooks();
        generateKnowledgeBase();
    }
}