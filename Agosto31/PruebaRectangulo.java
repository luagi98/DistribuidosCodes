public class PruebaRectangulo {

    public static void main (String[] args) {

        Coordenada r1 = new Coordenada(2, 3);
        Coordenada r2 = new Coordenada(5, 1);
        Coordenada c1 = new Coordenada(-2, 2);
        Coordenada c2 = new Coordenada(2, -2);
        

        Rectangulo rect1 = new Rectangulo(r1,r2);
        Cuadrado cua1 = new Cuadrado(c1,c2);

        System.out.println(rect1.toString());
        System.out.println("El área del rectángulo es = " + rect1.area());
        System.out.println(cua1.toString());
        System.out.println("El área del cuadrado es = " + cua1.area());

    }

}
