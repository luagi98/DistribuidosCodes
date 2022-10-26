
// Java program to illustrate
// ThreadPool
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Task class to be executed (Step 1)

/**
 * Pair
 */
class Pair {
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

class SortByValue implements Comparator<Pair> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(Pair a, Pair b) {
        return b.i - a.i;
    }
}

class Task implements Runnable {
    int threadNumber;
    long lines;
    String fileName = "BIBLIA_COMPLETA.txt";

    public Task(int threadNumber, long lines) {
        this.threadNumber = threadNumber;
        this.lines = lines;
    }

    // Prints task name and sleeps for 1s
    // This Whole process is repeated 5 times
    public void run() {
        Map<Character, Integer> hm = new HashMap<>();
        File f = new File(fileName);
        try {
            long start = (long) Math.floor((lines / 5) * (threadNumber - 1)),
                    end = (long) Math.floor((lines / 5) * (threadNumber));

            // System.out.println("start: " + start + " end: " + end + " in thread " +
            // threadNumber);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            long n = 0;
            while ((line = br.readLine()) != null) {
                if (n < start) {
                    n++;
                } else if (n < end && n >= start) {
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
                    n++;
                } else if (n >= end)
                    break;
            }
            br.close();
            System.out.println("Caracteres distintos: " + hm.size() + " para Thread " + threadNumber);
            ArrayList<Pair> arr = new ArrayList<>();
            for (Map.Entry<Character, Integer> it : hm.entrySet()) {
                arr.add(new Pair(it.getKey(), it.getValue()));
            }

            Collections.sort(arr, new SortByValue());

            for (Pair p : arr) {
                System.out.println("Thread: " + threadNumber + ' ' + p.toString());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

public class ConcurrentMap {
    // Maximum number of threads in thread pool
    static final int MAX_T = 3;

    public static void main(String[] args) {
        BufferedReader reader;
        long lines = 0;
        try {
            reader = new BufferedReader(new FileReader("BIBLIA_COMPLETA.txt"));
            while (reader.readLine() != null)
                lines++;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        // creates five tasks
        Runnable r1 = new Task(1, lines);
        Runnable r2 = new Task(2, lines);
        Runnable r3 = new Task(3, lines);
        Runnable r4 = new Task(4, lines);
        Runnable r5 = new Task(5, lines);

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);
        // t1.start();
        // t2.start();
        // t3.start();
        // t4.start();
        // t5.start();
        // pool shutdown ( Step 4)
        pool.shutdown();
    }
}
