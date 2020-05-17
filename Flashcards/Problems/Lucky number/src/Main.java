import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        String numStr = sc.nextLine();
        String[] numChars = numStr.split("");
        List<String> numStrList = Arrays.asList(numChars);
        List<Integer> numList = numStrList.stream().map(Integer::parseInt).collect(Collectors.toList());
        int firstHalfSum = numList.subList(0, numList.size()/2).stream().mapToInt(Integer::intValue).sum();
        int secondHalfSum = numList.subList(numList.size()/2, numList.size()).stream().mapToInt(Integer::intValue).sum();
        if(firstHalfSum == secondHalfSum) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}