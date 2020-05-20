package encryptdecrypt;

import java.io.*;
import java.nio.Buffer;

public class Main {

    public static void main(String[] args) {
        StringBuilder inputStr = new StringBuilder();
        int shiftNumber = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                String line = br.readLine();
                inputStr.append(line);
                shiftNumber = Integer.parseInt(br.readLine());
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String msg = inputStr.toString();
        StringBuilder sb = new StringBuilder();

        for(char c: msg.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                int shiftedCharNum = ( c - 'a' + shiftNumber ) % ('z' - 'a' + 1) + 'a' ;
                //System.out.println(shiftedCharNum);
                char encryptedChar = (char)(shiftedCharNum);
                //System.out.println(encryptedChar);
                sb.append(encryptedChar);
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb.toString());

    }
}
