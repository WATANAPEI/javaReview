import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        List<String> result = new ArrayList();
        while(true) {
            String str = sc.nextLine();
            try {
                int num = Integer.parseInt(str) * 10;
                if(num == 0) {
                    break;
                }
                result.add("" + num);
            } catch(NumberFormatException e) {
                result.add("Invalid user input: " + str);
            }
        }
        result.forEach(e -> System.out.println(e));
    }
}