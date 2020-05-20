package encryptdecrypt;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> getInputLines() {
        String mode = null;
        String targetStrings = null;
        String shiftNumber = null;
        List<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            mode = br.readLine();
            list.add(mode);
            targetStrings = br.readLine();
            list.add(targetStrings);
            shiftNumber = br.readLine();
            list.add(shiftNumber);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return list;
    }

    public static String encryptCaesar(String in, int shiftNumber) {
        StringBuilder sb = new StringBuilder();
        for(char c: in.toCharArray()) {
            int shiftedCharNum = c + shiftNumber;
            //System.out.println(shiftedCharNum);
            char encryptedChar = (char)(shiftedCharNum);
            //System.out.println(encryptedChar);
            sb.append(encryptedChar);
        }
        return sb.toString();
    }

    public static String decryptCaesar(String in, int shiftNumber) {
        StringBuilder sb = new StringBuilder();
        for(char c: in.toCharArray()) {
            int shiftedCharNum = c - shiftNumber;
            //System.out.println(shiftedCharNum);
            char encryptedChar = (char)(shiftedCharNum);
            //System.out.println(encryptedChar);
            sb.append(encryptedChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> list = getInputLines();
        int shiftNumber = Integer.parseInt(list.get(2));
        String mode = list.get(0);
        if(mode.contentEquals("enc")) {
            System.out.println(encryptCaesar(list.get(1), shiftNumber));
        } else if (mode.contentEquals("dec")) {
            System.out.println(decryptCaesar(list.get(1), shiftNumber));
        } else {
            System.out.println("Unknown command");
        }


    }
}
