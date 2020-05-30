import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double R = scanner.nextDouble();
        double S = R * R * Math.PI;
        System.out.println(S);
    }
}