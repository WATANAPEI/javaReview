import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] inArray = new int[n];
        int idx = 0;
        for ( int i = 0; i < n; ++i) {
            inArray[i] = scanner.nextInt();
        }

        int max = -100000000;
        for(int i = 0; i < inArray.length; ++i) {
            if ( inArray[i] >  max) {
                idx = i;
                max = inArray[i];
            }
        }
        System.out.println(idx);

    }
}