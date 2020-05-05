import java.util.Scanner;

public class Main {

    public static void print(String strArg) {
        System.out.println("print(\"" + strArg + "\")");
    }

    // write your method here
    public static void print(String strArg, int num) {
        System.out.println("print(\"" + strArg + "\", " + num + ")");
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int val = scanner.nextInt();
        print(str);
        print(str, val);
    }
}