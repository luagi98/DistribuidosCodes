public class Cuadrado extends Figura {

    public Cuadrado(Coordenada supIzq, Coordenada infDer) {
        super(supIzq, infDer);
    }

    @Override
    public double area() {
        double lado=getSupIzq().ordenada()-getInfDer().ordenada();
        return lado*lado;
    }     
}
