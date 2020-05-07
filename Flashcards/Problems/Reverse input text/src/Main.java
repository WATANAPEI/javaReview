import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // start coding here
            StringBuffer stringBuffer = new StringBuffer("");
            int c = reader.read();
            while(c != -1) {
                stringBuffer.append((char)c);
                c = reader.read();
            }
            System.out.println(stringBuffer.reverse());

        }
    }
}