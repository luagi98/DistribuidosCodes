
/*
 * Proyecto 2 Parte 1
 * 
 * Garcia Ibañez Luis Arturo
 * 
 * 4CM13
 */
import java.util.Scanner;

public class Executer {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String COLOR_RESET = "\u001B[0m";

    public static void printGoodSpelling(String s) {
        System.out.println(
                BLUE + "+++" + COLOR_RESET + " The word: \'" + GREEN + s + COLOR_RESET + "\' has good spelling");
    }

    public static void printBadSpelling(String s) {
        System.out
                .println(BLUE + "+++" + COLOR_RESET + " The word: \'" + RED + s + COLOR_RESET + "\' has bad spelling");
    }

    public static void main(String[] args) {
        SpellChecker sp = new SpellChecker();
        try (Scanner input = new Scanner(System.in, "UTF-8")) {
            while (true) {
                String line = input.nextLine();
                for (String word : line.split(" ")) {
                    if (sp.checkWord(word))
                        printGoodSpelling(word);
                    else
                        printBadSpelling(word);
                }
            }
        }

    }
}
