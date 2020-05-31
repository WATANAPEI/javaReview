import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        BigInteger a = new BigInteger(String.valueOf(sc.next()));
        BigInteger b = new BigInteger(String.valueOf(sc.next()));
        BigInteger c = new BigInteger(String.valueOf(sc.next()));
        BigInteger d = new BigInteger(String.valueOf(sc.next()));
        BigInteger result = a.negate().multiply(b).add(c).subtract(d);
        System.out.println(result);

    }
}