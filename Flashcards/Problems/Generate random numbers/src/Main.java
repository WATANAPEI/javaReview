import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        Random rand = new Random(a+b);
        int sum = 0;
        for(int i = 0; i < n; i++) {
            int randNum = rand.nextInt( b - a + 1) + a;
            sum += randNum;
        }
        System.out.println(sum);
    }
}