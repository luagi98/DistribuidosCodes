import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PoligonoIrreg {
    
    List<Coordenada> vertices;

    public PoligonoIrreg () {
        this.vertices = new ArrayList<Coordenada>();;
    }

    public void anadeVertice(Coordenada vertice) {
        vertices.add(vertice);
    }

    @Override
    public String toString() {
        
       String string = "";
       for (Coordenada coordenada : vertices) {
           string += coordenada.toString()+"\n";
       }
       return string;
    }

    void sort() {
        Collections.sort(vertices, new sortByMagnitud());
    }

    public static void main(String[] args) {
        int n = 10;
        int max = 100;
        int min = -100;
        PoligonoIrreg poli = new PoligonoIrreg();
        System.out.println("Generados");
        for (int i = 0; i < n; i++) {
            Coordenada aux = new Coordenada(Math.random()*(max-min+1)+min, Math.random()*(max-min+1)+min);
            poli.anadeVertice(aux);
        }
        System.out.println("Poligono");
        System.out.println(poli.toString());
        poli.sort();
        System.out.println(poli.toString());

    }

    public class sortByMagnitud implements Comparator<Coordenada>{

        @Override
        public int compare(Coordenada o1, Coordenada o2) {
            return (int) Math.floor(o1.getMagnitud()-o2.getMagnitud());
        }
    
    }
}
