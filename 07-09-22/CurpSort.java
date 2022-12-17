import java.util.ArrayList;
import java.util.Iterator;

public class CurpSort{
    // Función para generar una CURP aleatoria

    static String getCURP(){

        String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Numero = "0123456789";
        String Sexo = "HM";
        String Entidad[] = {"AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS"};

        int indice;
        StringBuilder sb = new StringBuilder(18);
        for (int i = 1; i < 5; i++) {
            indice = (int) (Letra.length()* Math.random());
            sb.append(Letra.charAt(indice));        
        }
        for (int i = 5; i < 11; i++) {
            indice = (int) (Numero.length()* Math.random());
            sb.append(Numero.charAt(indice));        
        }
        indice = (int) (Sexo.length()* Math.random());
        sb.append(Sexo.charAt(indice));        
        sb.append(Entidad[(int)(Math.random()*32)]);
        for (int i = 14; i < 17; i++) {
            indice = (int) (Letra.length()* Math.random());
            sb.append(Letra.charAt(indice));        
        }

        for (int i = 17; i < 19; i++) {
            indice = (int) (Numero.length()* Math.random());
            sb.append(Numero.charAt(indice));        
        }
        return sb.toString();
    }
    
    public static void main(String[] args){                   

        int n = Integer.parseInt(args[0]);
        ArrayList<String> curps = new ArrayList<>();

        System.out.println("Curps Generados");
        System.out.println();
        for (int i = 0; i < n; i++) {
            String curp = getCURP();
            System.out.println(curp);
            int c = curps.size();
            if (curps.size() > 0) {
                boolean hasIns = false;
                for (int j = 0; j < c; j++) {
                    String stringCompared = curps.get(j);
                    if (curp.compareTo(stringCompared) < 0) {
                        curps.add(j,curp);
                        hasIns = true;
                        break;                   
                    }
                }
                if(!hasIns) curps.add(curp);
            } else {
                curps.add(curp);
            }
        }
        System.out.println("Curps Ordenados");
        for (String curpI : curps) {
            System.out.println(curpI);
        }
    }
}