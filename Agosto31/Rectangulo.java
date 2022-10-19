public class Rectangulo extends Figura {

    public Rectangulo(Coordenada supIzq, Coordenada infDer) {
        super(supIzq, infDer);
    }

    @Override
    public double area() {
        double altura=getSupIzq().ordenada()-getInfDer().ordenada();
        double ancho=getSupIzq().abcisa()-getInfDer().abcisa();

        return Math.abs(altura*ancho);
    }
}
