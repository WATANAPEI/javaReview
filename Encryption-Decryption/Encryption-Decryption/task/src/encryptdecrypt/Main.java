package encryptdecrypt;

import java.io.*;
import java.util.*;

public class Main {

    public static Map<String, String> getOpts(String[] args) {
        List<String> stringList = Arrays.asList(args);
        Map<String, String> optMap = new HashMap<>();
        int modePos = stringList.indexOf("-mode");
        int keyPos = stringList.indexOf("-key");
        int dataPos = stringList.indexOf("-data");
        if(modePos == -1) {
            optMap.put("mode", "enc");
        } else {
            optMap.put("mode", stringList.get(modePos+1));
        }

        if(keyPos == -1) {
            optMap.put("key", "0");
        } else {
            optMap.put("key", stringList.get(keyPos+1));
        }
        if(dataPos == -1) {
            optMap.put("data", "");
        } else {
            optMap.put("data", stringList.get(dataPos+1));
        }
        return optMap;

    }

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

    public static void main(String[] args) {
        Map<String, String> opts = getOpts(args);
        String mode = opts.get("mode");
        String stringToConvert = opts.get("data");
        int shiftNumber = Integer.parseInt(opts.get("key"));
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




