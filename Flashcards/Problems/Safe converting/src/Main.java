import java.util.Scanner;

public class Main {

    public static int convert(Long val) {
        // write your code here
        int result = 0;
        if(val == null) {
            return result;
        } else if(val > Integer.MAX_VALUE) {
            return (int)Integer.MAX_VALUE;
        } else if (val < Integer.MIN_VALUE) {
            return (int)Integer.MIN_VALUE;
        } else {
            return (int) (long) val;
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val = scanner.nextLine();
        Long longVal = "null".equals(val) ? null : Long.parseLong(val);
        System.out.println(convert(longVal));
    }
}