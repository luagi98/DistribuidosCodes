import java.util.ArrayList;
import java.util.Iterator;

public class Curp{
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
        char sexo = args[1].charAt(0);

        ArrayList<String> curps = new ArrayList<>();

        System.out.println("Curps Generados");
        System.out.println();
        for (int i = 0; i < n; i++) {
            String curpGenerado = getCURP();
            curps.add(curpGenerado);
            System.out.println(curpGenerado);
        }
        Iterator<String> it = curps.iterator();

        while (it.hasNext()) {
            String curp = it.next();
            if(curp.charAt(10) == sexo) {
                it.remove();
            }
        }

        System.out.println("Curps despues del filtro");
        System.out.println();

        for (String curp : curps) {
            System.out.println(curp);
        }
    }
}