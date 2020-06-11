import java.awt.desktop.SystemSleepEvent;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();

        // write your code here
        /*
        line = line.replaceAll("[\\.,!\\?]", "");
        String regexStr = "\\s+[a-zA-Z]{" + String.valueOf(size) + "}\\s+";
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(" " + line + " ");
        if(matcher.find()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
         */
        String[] strArray = line.replaceAll("[^a-zA-Z\\s]", "").split("\\s+");
        for(int i = 0;i < strArray.length; i++) {
            if(strArray[i].length() == size)  {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}