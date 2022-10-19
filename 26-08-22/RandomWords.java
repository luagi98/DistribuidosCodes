/**
 * RandomWords
 */
public class RandomWords {

    static int timesFindedChar(int n){
        long start = System.nanoTime();
        char [] words = new char[n*4];
		for(int i = 1; i <= words.length; i++) {
            char letra;
            if(i % 4 != 0) {
                letra = (char)((Math.random()*(26-1)+1) + 64);
            } else{
                letra = (char) 32;
            }
            words[i-1] = letra;
		}
        
        int nTimes = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] == 'I') if (words[i+1] == 'P') if (words[i+2] == 'N') nTimes++;
        }
        long end = System.nanoTime();
        System.out.println("Tiempo con Char[]: "+(end-start)+" nanosegundos");
        return nTimes;
    }

    static int timesFindedStringBuilder(int n) {
        long start = System.nanoTime();
        int occurencies = 0;
        StringBuilder words = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char [] letras = new char[4];
            for (int j = 0; j < letras.length; j++) {
                if(i != 3) 
                letras[j] = (char)((Math.random()*(26-1)+1) + 64);
                else 
                letras[j] = (char) 32;
            }
            words.append(letras);
        }
        String ipn = "IPN";
        
        for(int i = words.indexOf(ipn); i >= 0; i = words.indexOf(ipn, i + 1)) {
            occurencies++;
        }
        long end = System.nanoTime();
        System.out.println("Tiempo con StringBuilder: "+(end-start)+" nanosegundos");
        return occurencies;
    }

    public static void main(String[] args) {
        
        int n = Integer.parseInt(args[0]);
        System.out.println(timesFindedChar(n));
        System.out.println(timesFindedStringBuilder(n));
    }
}