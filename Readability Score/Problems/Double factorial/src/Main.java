import java.math.BigInteger;

class Factorial {
    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here
        if(n == 0 || n == 1) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(n).multiply(calcDoubleFactorial(n - 2) );
        }
    }
}
/*
class Main {
    public static void main(String[] args) {
        int n = 7;
        System.out.println(Factorial.calcDoubleFactorial(n));
    }
}
 */