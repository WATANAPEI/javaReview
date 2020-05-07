import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // start coding here
            StringBuffer stringBuffer = new StringBuffer("");
            int c = reader.read();
            while( c != -1) {
                //System.out.println(c);
                stringBuffer.append((char)c);
                c = reader.read();
            }
            //System.out.println(c);
            String[] strings = stringBuffer.toString().trim().split("\\s+");
            if(stringBuffer.toString().trim().isEmpty()) {
                System.out.println("0");
            } else {
                System.out.println(strings.length);
            }
        }
    }

}