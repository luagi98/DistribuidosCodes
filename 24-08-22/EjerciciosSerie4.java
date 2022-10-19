public class EjerciciosSerie4 {

    public static void main(String[] args) {
        int n = 20;
        // fibo(n);
        int fi = 0, se = 1, th = 1, res;
        System.out.println(fi);
        System.out.println(se);
        System.out.println(th);
        for (int i = 1; i <= 17; i++) {
            res = fi+se+th;
            fi = se;
            se = th;
            th = res;
            System.out.println(res);  
        }
    }
}
