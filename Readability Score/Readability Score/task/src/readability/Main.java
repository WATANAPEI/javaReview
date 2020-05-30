package readability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] txt = sc.nextLine().split("[\\.!\\?]");
        int avgLength = 0;
        List<Integer> sentenceLengthList = new ArrayList<>();
        List<String> sentenceList = Arrays.asList(txt);
        for(int i = 0; i < sentenceList.size(); i++){
            sentenceLengthList.add(sentenceList.get(i).split("\\s+").length);
        }
        int sum = 0;
        for(int i = 0; i < sentenceLengthList.size(); i++) {
            sum += sentenceLengthList.get(i);
        }
        avgLength = sum / sentenceLengthList.size();
        if(avgLength > 10) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }
}
