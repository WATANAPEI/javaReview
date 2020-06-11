import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringWithHTMLTags = scanner.nextLine();

        // write your code here
        String line = stringWithHTMLTags.replaceAll("\\<.*?\\>", "");
        System.out.println(line);
    }
}