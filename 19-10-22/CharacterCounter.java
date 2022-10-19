import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CharacterCounter {

    /**
     * Pair
     */
    public static class Pair {
        Character c;
        Integer i;

        public Pair(Character c, Integer i) {
            this.c = c;
            this.i = i;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "character: " + c + ", value: " + i;
        }
    }

    public static class SortByValue implements Comparator<Pair> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Pair a, Pair b) {
            return b.i - a.i;
        }
    }

    public static void main(String[] args) {
        Map<Character, Integer> hm = new HashMap<>();
        File f = new File("El_viejo_y_el_mar.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    int value;
                    char key = line.charAt(i);
                    if (hm.containsKey(key)) {
                        value = hm.get(key);
                    } else {
                        value = 0;
                    }
                    hm.put(key, value + 1);
                }
            }

            br.close();

            System.out.println("Numero de caracteres distintos: " + hm.size());

            ArrayList<Pair> arr = new ArrayList<>();
            for (Map.Entry<Character, Integer> it : hm.entrySet()) {
                System.out.println(it.getKey() + ": " + it.getValue());
                arr.add(new Pair(it.getKey(), it.getValue()));
            }

            Collections.sort(arr, new SortByValue());

            for (Pair p : arr) {
                System.out.println(p.toString());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
