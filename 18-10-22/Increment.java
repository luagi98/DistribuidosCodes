public class Increment {

    static int varCompartida = 0;

    public static class Worker implements Runnable {

        private int n;

        public Worker(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 0; i < n; i++) {
                modifica();
            }
        }

        public synchronized void modifica() {
            if (Thread.currentThread().getName().equals("thread1"))
                varCompartida++;
            else
                varCompartida--;
        }

    }

    public static void main(String[] args) {
        Worker w;

        int n = Integer.parseInt(args[0]);

        w = new Worker(n);

        Thread t1 = new Thread(w, "thread1");
        Thread t2 = new Thread(w, "thread2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println("value varCompartida " + ": " + varCompartida);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
