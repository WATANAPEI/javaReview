import java.util.Scanner;
import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int dictNum = scanner.nextInt(); scanner.nextLine();
        Set<String> dict = new HashSet();
        for(int i = 0;i < dictNum; i++) {
            dict.add(scanner.next().toLowerCase());
        }

        int lineNum = scanner.nextInt(); scanner.nextLine();
        Set<String> result = new HashSet();
        for(int i = 0; i < lineNum; i++) {
            List<String> wordList = Arrays.asList(scanner.nextLine().split("\\s+"));
            result.addAll(wordList);
        }

        result.removeIf(e -> dict.contains(e.toLowerCase()));
        result.forEach(e -> System.out.println(e));

    }
}