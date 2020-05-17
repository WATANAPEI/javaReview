import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        int length = line2.length();
        int count = 0;
        for(int i = 0; i < line1.length() - length + 1; i++) {
            String substr = line1.substring(i, i+length);
            //System.out.println(substr);
            if(substr.contentEquals(line2)) {
                count++;
            }
        }
        System.out.println(count);
    }
}