import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) {
        String path = "./dataset_91007.txt";
        File file = new File(path);
        int max = 0;
        int num = 0;
        try(Scanner sc = new Scanner(file)) {
            num = sc.nextInt();
            while(sc.hasNext()) {
                if(max < num) {
                    max = num;
                }
                num = sc.nextInt();
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not exist");
        }
        System.out.println(max);
    }
}
