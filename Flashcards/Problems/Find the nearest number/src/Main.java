import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        List<String> line1 = Arrays.asList(sc.nextLine().split("\\s+"));
        List<Integer> numList = new ArrayList<>();
        for (var e : line1) {
            numList.add(Integer.parseInt(e));
        }
        int pivot = sc.nextInt();
        //sc.nextLine();
        numList.sort(Comparator.naturalOrder());
        //numList.forEach(e -> System.out.print(e + " "));
        //System.out.println("");
        var diffList = numList
                .stream()
                .map(e -> e - pivot)
                .map(e -> Math.abs(e))
                .collect(Collectors.toList());
        //diffList.forEach(e -> System.out.print(e + " "));
        //System.out.println("");

        List<Integer> minList = new ArrayList();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < diffList.size(); i++) {
            var e = diffList.get(i);
            if(e < min) {
                minList.clear();
                min = e;
                minList.add(i);
            } else if (min == e) {
                minList.add(i);
            }
        }
        //minList.forEach(e -> System.out.print(e + " "));
        //System.out.println(" ");
        for(var e: minList) {
            System.out.print(numList.get(e) + " ");
        }


    }
}
