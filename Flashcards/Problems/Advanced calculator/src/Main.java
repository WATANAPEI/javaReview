import java.util.*;
import java.util.stream.Collectors;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        // write your code here
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < args.length; i++) {
            list.add(Integer.parseInt(args[i]));
        }
        Optional<Integer> result;
        if(operator.contentEquals("MAX")) {
            result = list.stream().max(Comparator.naturalOrder());
            System.out.println(result.get());
        } else if (operator.contentEquals("MIN")) {
            result = list.stream().min(Comparator.naturalOrder());
            System.out.println(result.get());
        } else { // operator is "SUM"
            int streamResult = list.stream().mapToInt(Integer::intValue).sum();
            System.out.println(streamResult);
        }
    }
}