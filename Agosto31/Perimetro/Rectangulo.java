public class Rectangulo extends Figura implements Perimetro{

    public Rectangulo(Coordenada supIzq, Coordenada infDer) {
        super(supIzq, infDer);
    }

    @Override
    public double area() {
        double altura=getSupIzq().ordenada()-getInfDer().ordenada();
        double ancho=getSupIzq().abcisa()-getInfDer().abcisa();

        return Math.abs(altura*ancho);
    }

    @Override
    public float imprimePerimetro() {
        float altura=Math.abs((float)(getSupIzq().ordenada()-getInfDer().ordenada()));
        float ancho=Math.abs((float)(getSupIzq().abcisa()-getInfDer().abcisa()));

        return (altura*2)+(ancho*2);
    }
}
