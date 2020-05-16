import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void sort(int[] numbers) {
        // write your code here
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        list.sort(Comparator.naturalOrder());
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = list.get(i);
        }

    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split("\\s+");
        int[] numbers = Arrays.stream(values)
                .mapToInt(Integer::parseInt)
                .toArray();
        sort(numbers);
        Arrays.stream(numbers).forEach(e -> System.out.print(e + " "));
    }
}