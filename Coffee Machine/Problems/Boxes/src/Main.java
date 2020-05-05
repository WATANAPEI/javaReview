import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] box1 = new int[3];
        int[] box2 = new int[3];
        for(int i = 0; i < 3; i++) {
            box1[i] = scanner.nextInt();
        }
        for(int i = 0; i < 3; i++) {
            box2[i] = scanner.nextInt();
        }
        Arrays.sort(box1);
        Arrays.sort(box2);
        // check box1 = box2
        boolean equal = true;
        for (int i = 0; i < 3; ++i) {
            if(box1[i] != box2[i]) {
                equal = false;
                break;
            }
        }
        if(equal) {
            System.out.println("Box 1 = Box 2");
            return;
        }
        // check box 1 < box 2
        boolean less = true;
        for (int i = 0; i < 3; ++i) {
            if(box1[i] > box2[i]) {
                less = false;
                break;
            }
        }
        if(less) {
            System.out.println("Box 1 < Box 2");
            return;
        }

        // check box 1 > box 2
        boolean greater = true;
        for (int i = 0; i < 3; ++i) {
            if(box1[i] < box2[i]) {
                greater = false;
                break;
            }
        }
        if(greater) {
            System.out.println("Box 1 > Box 2");
            return;
        }
        System.out.println("Incomparable");

        // write your code here
    }
}