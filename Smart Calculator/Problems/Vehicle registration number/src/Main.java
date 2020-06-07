import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

        /* write your code here */
        String validExp = "[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}";
        boolean isMatched = regNum.matches(validExp);
        System.out.println(isMatched);
    }
}