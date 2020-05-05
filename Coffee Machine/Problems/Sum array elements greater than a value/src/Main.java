import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] inputArray = new int[n];
        for (int i = 0; i < n; ++i) {
            inputArray[i]= scanner.nextInt();
        }
        int sum = 0;
        int border = scanner.nextInt();
        for(int i: inputArray) {
            if ( i > border) {
                sum += i;
            }
        }
        System.out.println(sum);

    }
}