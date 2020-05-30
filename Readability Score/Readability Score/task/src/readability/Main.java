package readability;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // parse argument
        String filename = args[0];
        System.out.println("The text is:");
        String content = readAll(filename);
        System.out.println(content);

        List<String> sentenceList = parseSentences(content);
        List<String> wordList = parseWords(content);
        int charNum = countCharacters(content);
        double score =  calculateScore(charNum, wordList.size(), sentenceList.size());

        System.out.println("Words: " + wordList.size());
        System.out.println("Sentences: " + sentenceList.size());
        System.out.println("Characters: " + charNum);
        System.out.println("The score is: " + score);
        System.out.println("This text should be understood by " + suitableAge(score) + " year olds");

    }

    private static String readAll(String path) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str = br.readLine();
            while(str != null) {
                sb.append(str);
                str = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private static double calculateScore(int characters, int words, int sentences) {
        return 4.71 * characters / words + 0.5 *  words / sentences - 21.43;
    }
    private static String suitableAge(double score) {
        if (score <= 1) {
            return "5-6";
        } else if (1 < score && score <= 2) {
            return "6-7";
        } else if (2 < score && score <= 3) {
            return "7-9";
        } else if (3 < score && score <= 4) {
            return "9-10";
        } else if (4 < score && score <= 5) {
            return "10-11";
        } else if (5 < score && score <= 6) {
            return "11-12";
        } else if (6 < score && score <= 7) {
            return "12-13";
        } else if (7 < score && score <= 8) {
            return "13-14";
        } else if (8 < score && score <= 9) {
            return "14-15";
        } else if (9 < score && score <= 10) {
            return "15-16";
        } else if (10 < score && score <= 11) {
            return "16-17";
        } else if (11 < score && score <= 12) {
            return "17-18";
        } else if (12 < score && score <= 13) {
            return "18-24";
        } else {
            return "24+";
        }
    }

    private static List<String> parseSentences(String string) {
        String[] strArray = string.split("\\.|\\?|!");
        List<String> sentenceList = Arrays.asList(strArray);
        return sentenceList;
    }

    private static List<String> parseWords(String string) {
        String[] strArray = string.split("\\s+");
        List<String> wordsList = Arrays.asList(strArray);
        return wordsList;
    }

    private  static int countCharacters(String string) {
        String regex = "[\\t\\n\\s]";
        Stream<String> stringStream = Stream.of(string);
        /*
        String filtered = stringStream.filter(e -> e.matches(regex))
            .collect(Collectors.joining());
         */
        String filtered = string.replaceAll(regex, "");
        return filtered.length();
    }
}
