package encryptdecrypt;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String mode = list.get(0);
        String stringToConvert = list.get(1);
        int shiftNumber = Integer.parseInt(list.get(2));
        Map<String, Command> commandMap = new HashMap();
        commandMap.put("enc", Command.Enc);
        commandMap.put("dec", Command.Dec);
        String convertedStr = commandMap.get(mode).convertStr(stringToConvert, shiftNumber);
        System.out.println(convertedStr);

    }
}

enum Command {
    Enc {
        @Override
        public String convertStr(String in, int shiftNumber) {
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
    },
    Dec {
        @Override
        public String convertStr(String in, int shiftNumber) {
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

    };

    public abstract String convertStr(String in, int shiftNumber);
}




