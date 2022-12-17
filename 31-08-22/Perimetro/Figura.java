/**
 * figura
 */
public abstract class Figura {

    private Coordenada supIzq, infDer;

    public Figura(Coordenada supIzq, Coordenada infDer){
        this.supIzq = supIzq;
        this.infDer = infDer;
    }

    public Coordenada getInfDer() {
        return infDer;
    }

    public Coordenada getSupIzq() {
        return supIzq;
    }

    public abstract double area();

    @Override
    public String toString( ) {

        return "Esquina superior izquierda: " + supIzq + "\tEsquina inferior derecha:" + infDer + "\n";

    }
}