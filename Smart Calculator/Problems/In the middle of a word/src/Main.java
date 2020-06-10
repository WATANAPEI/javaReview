import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();

        // write your code here
        //String regexStr = "([a-zA-Z]+\\s)+.+" + part  + ".+(\\s[a-zA-Z]+)";
        //String regexStr = "([a-zA-Z]*\\s+)*" + part + "([a-zA-Z]*\\s+)*";
        String regexStr = "[a-zA-Z]+" + part + "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(part, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}