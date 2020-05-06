import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        // Write your code here
        SortedMap<Integer, String> result;
        if(map.firstKey() %2 != 0) {
            result = map.subMap(map.firstKey(), map.firstKey()+5);
        } else {
            result = map.subMap(map.lastKey()-4, map.lastKey()+1);
        }
        TreeMap<Integer, String> clone = new TreeMap(Collections.reverseOrder());
        result.forEach((k, v) -> clone.put(k, v));
        return clone;
    }
}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}