import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        List<String> listString = Arrays.asList(line);
        int longestLength = 0;
        String longestWord = "";
        for(String e: listString)  {
            if(e.length() > longestLength) {
                longestLength = e.length();
                longestWord = e;
            }
        }
        System.out.println(longestWord);

    }
}