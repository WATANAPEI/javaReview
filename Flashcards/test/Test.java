import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        int r = 7;
        int[] a = {3, 0, 3, 9, 2, 1};
        for(int i = 0; i < a.length; i++) {
            int x = a[i];
            x = x * x - x;
            r += x;
        }
        System.out.println(r);

        int s = 13;
        s += a[a[0]];
        s -= a[a[a.length - 1]];
        System.out.println(s);

        int t = 0;
        for (int i = 0; i < a.length; i++) {
            if ( a[i] < a.length) {
                t += a[a[i]];
            }
        }
        System.out.println(t);

        int[] b = {9, 8, 3, 1, 5, 4};
        for(int i = 0; i < b.length; i++) {
            if(b[i] %2 == 0) {
                b[i] += 1;
            } else if ( b[i] < b.length) {
                b[i] += b[b[i]];
            }
        }
        for(int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }

    }
}
