package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        while(true) {
            String[] input = scanner.nextLine().split("\\s+");
            if(input.length != 1) {
                int[] num = new int[input.length];
                int sum = 0;
                for(int j = 0; j < input.length; j++) {
                    num[j] = Integer.parseInt(input[j]);
                    sum += num[j];
                }
                System.out.println(sum);
            } else if (input.length == 1) {
                if(input[0].contentEquals("/exit")) {
                    System.out.println("Bye!");
                    break;
                } else if (input[0].contentEquals("")) {
                    continue;
                } else if (input[0].contentEquals("/help")){
                    System.out.println("The program calculates the sum of numbers");
                } else {
                    System.out.println(input[0]);
                }
            }
        }
    }
}
