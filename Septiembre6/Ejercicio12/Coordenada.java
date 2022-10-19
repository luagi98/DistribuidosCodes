public class Coordenada {

    private Double x, y;
    private Double magnitud;

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
        this.magnitud = Math.sqrt(x*x+y*y);
    }

    //Metodo getter de x

    public double abcisa( ) { return x; }

 

    //Metodo getter de y

    public double ordenada( ) { return y; }

    public double getMagnitud() { return magnitud; }

    public String format(double d) {return String.format("%.3f",d);}

    @Override

    public String toString( ) {

        return "[" + format(x) + "," + format(y) + "] Dist: "+ format(magnitud);

    }

}
