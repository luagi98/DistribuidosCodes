import java.util.ArrayList;
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

    public static void main(String[] args) {
        PoligonoIrreg poli = new PoligonoIrreg();
        System.out.println("Generados");
        for (int i = 0; i < 10; i++) {
            Coordenada aux = new Coordenada(Math.random()*(50-1)+1, Math.random()*(50-1)+1);
            poli.anadeVertice(aux);
            System.out.println(aux.toString());
        }
        System.out.println("Poligono");
        System.out.println(poli.toString());;
    }

}
