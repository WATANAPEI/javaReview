package readability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CalculateScore {
    Calculable strategy;

    CalculateScore(String command, Context context) {
        switch(command) {
            case "ARI":
                this.strategy = new CalculateARI(context);
                break;
            case "FK":
                this.strategy = new CalculateFK(context);
                break;
            case "SMOG":
                this.strategy = new CalculateSMOG(context);
                break;
            case "CL":
                this.strategy = new CalculateCL(context);
                break;
            case "all":
                this.strategy = new CalcuateAll(context);
                break;
            default:
                this.strategy = null;
        }
    }

    public void calculateAndPrintResult() {
        strategy.calculate();
        strategy.printResult();
        System.out.println("");
        strategy.printSuitableAge();
    }
}

interface Calculable {
    double calculate();
    void printResult();
    double getSuitableAge();
    void printSuitableAge();
    default void printUnderstandableAge(double avgOlds) {
        System.out.println("This text should be understood in average by "
                + avgOlds + " year olds.");

    };
}

class CalculateARI implements Calculable {
    Context context;
    double result;
    double suitableAge;

    public CalculateARI(Context context) {
        this.context = context;
        this.result = 0;
    }

    @Override
    public double calculate() {
        this.result = 4.71 * context.getCharNum() / context.getWordNum()
                + 0.5 *  context.getWordNum() / context.getSentenceNum() - 21.43;
        this.suitableAge = SuitableAge.getSuitableAge(this.result);
        return this.result;
    }

    @Override
    public double getSuitableAge() {
        return this.suitableAge;
    }

    @Override
    public void printResult() {
        System.out.println("Automated Readability Index: " + this.result +
                " (about " + (int)this.suitableAge + " year olds)." );
    }

    @Override
    public void printSuitableAge() {
        printUnderstandableAge(this.suitableAge);
    }
}

class CalculateFK implements Calculable {
    Context context;
    double result;
    double suitableAge;

    public CalculateFK(Context context) {
        this.context = context;
    }

    @Override
    public double calculate() {
        this.result = 0.39 * context.getWordNum() / context.getSentenceNum()
                + 11.8 *  context.getSyllableCount() / context.getWordNum() - 15.59;
        this.suitableAge = SuitableAge.getSuitableAge(this.result);
        return this.result;
    }

    @Override
    public double getSuitableAge() {
        return this.suitableAge;
    }

    @Override
    public void printResult() {
        System.out.println("Flesch–Kincaid readability tests: " + this.result +
                " (about " + (int)this.suitableAge + " year olds)." );
    }

    @Override
    public void printSuitableAge() {
        printUnderstandableAge(this.suitableAge);
    }

}

class CalculateSMOG implements Calculable {
    Context context;
    double result;
    double suitableAge;

    public CalculateSMOG(Context context) {
        this.context = context;
    }

    @Override
    public double calculate() {
        this.result = 1.043 * Math.sqrt((double)context.getPolysyllableCount() * 30 / (double)context.getSentenceNum())
                + 3.1291;
        this.suitableAge = SuitableAge.getSuitableAge(this.result);
        return this.result;
    }

    @Override
    public void printResult() {
        System.out.println("Simple Measure of Gobbledygook: " + this.result +
                " (about " + (int)this.suitableAge + " year olds)." );
    }

    @Override
    public double getSuitableAge() {
        return this.suitableAge;
    }

    @Override
    public void printSuitableAge(){
        printUnderstandableAge(this.suitableAge);
    }

}

class CalculateCL implements Calculable {
    Context context;
    double result;
    double suitableAge;

    public CalculateCL(Context context) {
        this.context = context;
    }

    @Override
    public double calculate() {
        double L = (double)context.getCharNum() / (double)context.getWordNum() * 100;
        double S = (double)context.getSentenceNum() / (double)context.getWordNum() * 100;
        this.result = 0.0588 * L - 0.296 * S - 15.8;
        this.suitableAge = SuitableAge.getSuitableAge(this.result);
        return this.result;
    }

    @Override
    public void printResult() {
        System.out.println("Coleman–Liau index: " + this.result +
                " (about " + (int)this.suitableAge + " year olds)." );
    }

    @Override
    public double getSuitableAge() {
        return this.suitableAge;
    }

    @Override
    public void printSuitableAge(){
        printUnderstandableAge(this.result);
    }

}

class CalcuateAll implements Calculable {
    Context context;
    double result;
    CalculateARI ari;
    CalculateFK fk;
    CalculateCL cl;
    CalculateSMOG smog;
    double suitableAge;

    public CalcuateAll(Context context) {
        this.context = context;
        this.ari = new CalculateARI(context);
        this.fk = new CalculateFK(context);
        this.cl = new CalculateCL(context);
        this.smog = new CalculateSMOG(context);
    }

    @Override
    public double calculate() {
        List<Double> resultList = new ArrayList<>();
        resultList.add(ari.calculate());
        resultList.add(fk.calculate());
        resultList.add(cl.calculate());
        resultList.add(smog.calculate());
        this.result = resultList.stream().mapToDouble(x->x).average().getAsDouble();
        this.suitableAge = (ari.getSuitableAge() + fk.getSuitableAge() + cl.getSuitableAge() +
                smog.getSuitableAge()) / 4;
        return this.result;
    }

    @Override
    public double getSuitableAge() {
        return this.suitableAge;
    }

    @Override
    public void printResult() {
        ari.printResult();
        fk.printResult();
        smog.printResult();
        cl.printResult();
    }

    @Override
    public void printSuitableAge(){
        printUnderstandableAge(this.suitableAge);
    }

}

class SuitableAge{
    static int getSuitableAge(double score) {
        score = Math.round(score);
        switch ((int)score) {
            case 1:
                return 6;

            case 2:
                return 7;

            case 3:
                return 8;

            case 4:
                return 9;

            case 5:
                return 11;

            case 6:
                return 12;

            case 7:
                return 13;

            case 8:
                return 14;

            case 9:
                return 15;

            case 10:
                return 16;

            case 11:
                return 17;

            case 12:
                return 18;

            case 13:
                return 24;

            default:
                return 0;
        }
        /*
        if (score == 1D) {
            return 6;
        } else if (sco) {
            return 7;
        } else if (2 < score && score <= 3) {
            return 9;
        } else if (3 < score && score <= 4) {
            return 10;
        } else if (4 < score && score <= 5) {
            return 11;
        } else if (5 < score && score <= 6) {
            return 12;
        } else if (6 < score && score <= 7) {
            return 13;
        } else if (7 < score && score <= 8) {
            return 14;
        } else if (8 < score && score <= 9) {
            return 15;
        } else if (9 < score && score <= 10) {
            return 16;
        } else if (10 < score && score <= 11) {
            return 17;
        } else if (11 < score && score <= 12) {
            return 18;
        } else if (12 < score && score <= 13) {
            return 24;
        } else {
            return Integer.MAX_VALUE;
        }
         */
    }
}

class Context {
    String content;
    int wordNum;
    int sentenceNum;
    int charNum;
    int syllableCount;
    int polysyllableCount;

    public Context(String content) {
        this.content = content;
        this.wordNum = wordNumber();
        this.sentenceNum = sentenceNumber();
        this.charNum = charNumber();
        this.syllableCount = syllableNumber();
        this.polysyllableCount = polysyllableNumber();
    }

    public int getWordNum() {
        return this.wordNum;
    }
    public int getSentenceNum() {
        return this.sentenceNum;
    }
    public int getCharNum() {
        return this.charNum;
    }
    public int getSyllableCount() {
        return this.syllableCount;
    }
    public int getPolysyllableCount() {
        return this.polysyllableCount;
    }

    private int sentenceNumber() {
        return parseSentences(this.content).size();
    }

    private int wordNumber() {
        return parseWords(this.content).size();
    }

    private int charNumber() {
        return countCharacters(this.content);
    }

    private int syllableNumber() {
        return parseSyllables(this.content).stream().mapToInt(e->e).sum();
    }

    private int polysyllableNumber() {
        List<Integer> syllableList = parseSyllables(this.content);
        List<Integer> polysyllableList = syllableList.stream().filter(e -> e>2).collect(Collectors.toList());
        //polysyllableList.stream().forEach(e->System.out.println(e + " "));
        return polysyllableList.size();
    }

    private List<String> parseSentences(String string) {
        String[] strArray = string.split("\\.|\\?|!");
        List<String> sentenceList = Arrays.asList(strArray);
        return sentenceList;
    }

    private List<String> parseWords(String string) {
        String[] strArray = string.split("\\s+");
        List<String> wordsList = Arrays.asList(strArray);
        //wordsList.stream().map(e->e.replaceAll("[.,]", "")).forEach(e->System.out.println(e));
        List<String> filteredList =  wordsList.stream().map(e->e.replaceAll("[.,!?]", "")).collect(Collectors.toList());
        return filteredList;
    }

    private int countCharacters(String string) {
        String regex = "[\\t\\n\\s]";
        Stream<String> stringStream = Stream.of(string);
        String filtered = string.replaceAll(regex, "");
        return filtered.length();
    }

    private List<Integer> parseSyllables(String content) {
        List<String> wordsList = parseWords(content);
        List<Integer> syllableCountList = new ArrayList<>();
        String regex = "[aiueoyAIUEOY]([^aiueoyAIUEOY]|$)";
        Pattern pattern = Pattern.compile(regex);
        for(String e: wordsList) {
            Matcher m = pattern.matcher(e);
            int count = 0;
            while(m.find()) {
                if(!(m.group().contentEquals("e") && m.end() == e.length())) {
                    //System.out.println(e + ": " + m.group() + ": " + m.start() + "/" + m.end());
                    count++;
                }
            }
            if(count == 0) {
                count++;
            }
            syllableCountList.add(count);
        }
        return syllableCountList;
    }


}

public class Main {
    public static void main(String[] args) {
        // parse argument
        String filename = args[0];
        System.out.println("The text is:");
        String content = readAll(filename);
        System.out.println(content);

        //analyse content
        Context context = new Context(content);
        int wordNum = context.getWordNum();
        int sentenceNum = context.getSentenceNum();
        int charNum = context.getCharNum();
        int syllableCount = context.getSyllableCount();
        int polysyllableCount = context.getPolysyllableCount();

        // context statics
        System.out.println("Words: " + wordNum);
        System.out.println("Sentences: " + sentenceNum);
        System.out.println("Characters: " + charNum);
        System.out.println("Syllables: " + syllableCount);
        System.out.println("Polysyllables: " + polysyllableCount);

        //get command
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        System.out.println("");

        //calculate score according to command
        CalculateScore calculateScore = new CalculateScore(command, context);
        calculateScore.calculateAndPrintResult();


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

}
