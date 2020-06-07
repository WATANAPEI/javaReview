import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String regex = "^(-?\\d{1,3})*?\\.(-?\\d{1,3})*?\\.(-?\\d{1,3})*?\\.(\\d{1,3})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if(m.find()) {
            for(int i =1; i < 5; i++) {
                int ip = Integer.parseInt(m.group(i));
                if(ip >= 0 && ip < 256) {
                    //System.out.println("YES");
                } else {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");

        } else {
            System.out.println("NO");
        }
    }
}