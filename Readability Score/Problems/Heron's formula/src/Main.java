import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        double a = Double.parseDouble(sc.next());
        double b = Double.parseDouble(sc.next());
        double c = Double.parseDouble(sc.next());
        double p = (a + b + c) / 2;
        double S = Math.sqrt(p * (p-a) * (p-b) * (p-c));
        System.out.println(S);
    }
}