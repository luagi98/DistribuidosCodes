public class PoligonoIrreg {
    
    Coordenada [] vertices;

    public PoligonoIrreg () {
        this.vertices = null;
    }

    public void anadeVertice(Coordenada vertice) {
        Coordenada [] newVertices;
        int numVertices = vertices != null ? vertices.length : 0;
        newVertices = new Coordenada[numVertices+1];
        for (int i = 0; i < numVertices; i++) {
            newVertices[i] = vertices[i];
        }
        newVertices[numVertices] = vertice;
        vertices = newVertices;
    }

    @Override
    public String toString() {
        
       String string = "";
       for (int i = 0; i < vertices.length; i++) {
            string += vertices[i].toString()+"\n";
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
