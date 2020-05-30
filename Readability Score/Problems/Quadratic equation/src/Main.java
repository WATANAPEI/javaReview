import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        double a = Double.parseDouble(sc.next());
        double b = Double.parseDouble(sc.next());
        double c = Double.parseDouble(sc.next());
        double ans1 = (-b + Math.sqrt(b*b - 4 * a * c)) / (2 * a);
        double ans2 = (-b - Math.sqrt(b*b - 4 * a * c)) / (2 * a);
        if(ans1 > ans2) {
            System.out.println(ans2 + " " + ans1);
        } else {
            System.out.println(ans1 + " " + ans2);
        }

    }
}