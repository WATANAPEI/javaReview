import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        File file = new File("file.txt");

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.printf("%s dolor sit %s", "Lorem", "ipsum", "amet");
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
