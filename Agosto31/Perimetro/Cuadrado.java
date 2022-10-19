public class Cuadrado extends Figura implements Perimetro {

    public Cuadrado(Coordenada supIzq, Coordenada infDer) {
        super(supIzq, infDer);
    }

    @Override
    public double area() {
        double lado=getSupIzq().ordenada()-getInfDer().ordenada();
        return lado*lado;
    }

    @Override
    public float imprimePerimetro() {
        float lado=(float)(getSupIzq().ordenada()-getInfDer().ordenada());
        return Math.abs(lado*4);
    } 
}
