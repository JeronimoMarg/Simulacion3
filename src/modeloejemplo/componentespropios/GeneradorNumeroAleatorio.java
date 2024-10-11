package modeloejemplo.componentespropios;

public class GeneradorNumeroAleatorio {

    private static long a = 81; 
    private static long c = 7; 
    private static long m = (long) Math.pow(2, 16);
    private static long seed = 12934;

    public static double generarNumeroAleatorio(){
        seed = (a * seed + c) % m;
        return seed / (m-1.0);
    }

}
