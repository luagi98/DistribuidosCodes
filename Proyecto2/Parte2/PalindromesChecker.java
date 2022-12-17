/*
 * Proyecto 2 Parte 2
 * 
 * Garcia Ibañez Luis Arturo
 * 
 * 4CM11
 */

import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * PalindromeChecker
 */
public class PalindromesChecker {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String COLOR_RESET = "\u001B[0m";

    private void printPalindrome(String word) {
        System.out.println(BLUE + "+++ " + COLOR_RESET + GREEN + word + COLOR_RESET + " is palindrome.");
    }

    public boolean isPalindrome(String word) {
        return word.equals(new StringBuilder(word).reverse().toString()) && word.length() > 1;
    }

    public String checkException(String word) {
        word = word.replaceAll("[0-9]|[\\.]|-|_|,|/|\\*|\\^|\\(|\\)|;|:|\\$|%|\\?|¿|¡|\\!|—|[ ]", "");
        return word;
    }

    public void getPalindromesFromFile(String Filename) {
        try {
            Map<String, Boolean> dictionary = new HashMap<>();
            File file = new File(Filename);
            if (!file.exists()) {
                return;
            }
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                for (String word : line.split(" ")) {
                    String cleanedWord = checkException(word);
                    if (isPalindrome(cleanedWord) && !dictionary.containsKey(cleanedWord)) {
                        printPalindrome(cleanedWord);
                        dictionary.put(cleanedWord, true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PalindromesChecker pc = new PalindromesChecker();
        pc.getPalindromesFromFile(args[0]);
    }
}