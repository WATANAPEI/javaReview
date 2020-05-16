package flashcards;
import java.awt.desktop.SystemSleepEvent;
import java.io.*;
import java.util.*;

class CardDeck {

    private HashMap<String, String> deck;
    private HashMap<String, Integer> wrongCount;

    public CardDeck() {
        this.deck = new LinkedHashMap<>();
        this.wrongCount = new HashMap<>();
    }

    public void add(String key, String value) {
        this.deck.put(key, value);
    }

    public void addWithWrongCount(String key, String value, int wrongCount) {
        add(key, value);
        wrongAnswer(key, wrongCount);
    }

    public void remove(String key) throws NoCardFoundException {
        if (!deck.containsKey(key)) {
            throw new NoCardFoundException("Can't remove \"" + key + "\": there is no such card.");
        } else {
            deck.remove(key);
        }
    }

    public void removeWithWrongCount(String key) throws NoCardFoundException {
        remove(key);
        if(!wrongCount.containsKey(key)) {
            throw new NoCardFoundException("Can't remove \"" + key + "\": there is no such card.");
        } else {
            wrongCount.remove(key);
        }
    }

    public boolean containsKey(String key) {
        return this.deck.containsKey(key);
    }

    public boolean containsValue(String value) {
        return this.deck.containsValue(value);
    }

    public int size() {
        return this.deck.size();
    }

    public Set<String> keySet() {
        return this.deck.keySet();
    }

    public String get(String key) {
        return this.deck.get(key);
    }

    public void resetWrongCount() {
        for(var e: wrongCount.keySet()) {
            wrongCount.put(e, 0);
        }
    }

    public void wrongAnswer(String key, int wrongCount) {
        Integer count = this.wrongCount.getOrDefault(key, 0);
        this.wrongCount.put(key, count + wrongCount);
    }

    public int wrongCount(String key) {
        return this.wrongCount.get(key);
    }
}

class NoCardFoundException extends Exception {
    public NoCardFoundException(String msg) {
        super(msg);
    }
}

class TermExistException extends Exception {
    public TermExistException(String msg) {
        super(msg);
    }
}

class DefinitionExistException extends Exception {
    public DefinitionExistException(String msg) {
        super(msg);
    }
}
class CardDeckService {
    Logger log;
    private static CardDeckService cardDeckService = new CardDeckService();

    private CardDeckService() {
    }

    public static CardDeckService getInstance() {
        return cardDeckService;
    }

    public void addCard(CardDeck cardDeck) {
        String term = null;
        String def = null;
        try {
            term = _getCardString(cardDeck);
            def = _getDefString(cardDeck);
        } catch (TermExistException e) {
            log.printAndLog(e.getMessage());
            return;
        } catch (DefinitionExistException e) {
            log.printAndLog(e.getMessage());
            return;
        }
        cardDeck.addWithWrongCount(term, def, 0);
        log.printAndLog("The pair {\"" + term + "\":\"" + def + "\"} has been added.");
        log.printAndLog("");
    }

    private String _getCardString(CardDeck cardDeck) throws TermExistException {
        MyScanner sc = new MyScanner(this.log, System.in);
        log.printAndLog("The card:");
        String input = sc.nextLine();
        if(cardDeck.containsKey(input)) {
            throw new TermExistException("The card \"" + input + "\" already exists.");
        } else {
            return input;
        }
    }

    private String _getDefString(CardDeck cardDeck) throws DefinitionExistException{
        MyScanner sc = new MyScanner(this.log, System.in);
        log.printAndLog("The definition of the card:");
        String input = sc.nextLine();
        if(cardDeck.containsValue(input)) {
            throw new DefinitionExistException("The definition \"" + input + "\" already exists.");
        } else {
            return input;
        }
    }

    public void importDeck(CardDeck cardDeck) {
        log.printAndLog("File name:");
        MyScanner sc = new MyScanner(this.log, System.in);
        String  path = sc.nextLine();
        File file = new File(path);
        try {
            MyScanner scFile = new MyScanner(this.log, file);
            int count = 0;
            scFile.useDelimiter(",|\n");
            while(scFile.hasNext()) {
                String term = scFile.next();
                String def = scFile.next();
                int wrongCount = Integer.parseInt(scFile.next());
                try {
                    if(cardDeck.containsKey(term)) {
                        cardDeck.removeWithWrongCount(term);
                    }
                    cardDeck.addWithWrongCount(term, def, wrongCount);
                    ++count;
                } catch (NoCardFoundException e) {
                    ;
                }
            }
            log.printAndLog(count + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            log.printAndLog("File not found.");
        }
    }

    public void exportDeck(CardDeck cardDeck) {
        log.printAndLog("File name:");
        MyScanner sc = new MyScanner(this.log, System.in);
        File file = new File(sc.nextLine());
        try(PrintWriter printWriter = new PrintWriter(file)) {
            int count = 0;
            for(String term: cardDeck.keySet()) {
                printWriter.println(term + "," + cardDeck.get(term) + "," + cardDeck.wrongCount(term));
                ++count;
            }
            log.printAndLog(count + " cards have been saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCard(CardDeck cardDeck) {
        MyScanner sc = new MyScanner(this.log, System.in);
        log.printAndLog("The card:");
        String key = sc.nextLine();
        try {
            cardDeck.removeWithWrongCount(key);
            log.printAndLog("The card has been removed.");
        } catch (NoCardFoundException e) {
            log.printAndLog(e.getMessage());
        }
    }

    public void exit() {
        log.printAndLog("Bye bye!");
    }

    public void ask(CardDeck cardDeck) {
        log.printAndLog("How many times to ask?");
        MyScanner sc = new MyScanner(this.log, System.in);
        int askTime = sc.nextInt(); sc.nextLine();
        int asked = 0;
        List<String> questions = new ArrayList<String>(cardDeck.keySet());
        Random rand = new Random();
        int randomNum = rand.nextInt(questions.size());

        while(true) {
            String randomQuestionTerm = questions.get(randomNum);
            log.printAndLog("Print the definition of \"" + randomQuestionTerm + "\":");
            String ans = sc.nextLine();
            if(ans.equals(cardDeck.get(randomQuestionTerm))) {
                log.printAndLog("Correct answer.");
            } else {
                cardDeck.wrongAnswer(randomQuestionTerm, 1);
                if (cardDeck.containsValue(ans)) {
                    StringBuilder def = new StringBuilder();
                    for (String k : cardDeck.keySet()) {
                        if (cardDeck.get(k).equals(ans)) {
                            def.append(k);
                        }
                    }
                    log.printAndLog("Wrong answer. The correct one is \"" + cardDeck.get(randomQuestionTerm)
                            + "\", you've just written the definition of \"" + def + "\".");

                } else {
                    log.printAndLog("Wrong answer. The correct one is \"" + cardDeck.get(randomQuestionTerm) + "\".");
                }
            }
            asked++;
            randomNum = rand.nextInt(questions.size());

            if(asked == askTime) {
                break;
            }
        }
    }

    public void hardestCard(CardDeck cardDeck) {
        List<String> hardestTerm = new ArrayList<>();
        int hardest = 0;
        for(var e: cardDeck.keySet()) {
            int tmpCount = cardDeck.wrongCount(e);
            if(hardest < tmpCount) {
                hardestTerm.clear();
                hardest = tmpCount;
                hardestTerm.add(e);
            } else if (hardest == tmpCount) {
                hardestTerm.add(e);
            }
        }
        if(hardest == 0 ) {
            log.printAndLog("There are no cards with errors.");
        } else {
            if (hardestTerm.size() == 1) {
                log.printAndLog("The hardest card is \"" + hardestTerm.get(0) +
            "\". You have " + hardest + " errors answering it.");
            } else {
                String msg = "".join("\",\"", hardestTerm);
                log.printAndLog("The hardest cards are \"" + msg + "\". " +
                        "You have" + hardest + " errors answering them.");
            }
        }

    }

    public void resetStats(CardDeck cardDeck) {
        cardDeck.resetWrongCount();
        log.printAndLog("Card statistics has been reset.");
    }

    public void log(CardDeck cardDeck) {
        String msg = "File name:";
        log.printAndLog(msg);
        MyScanner sc = new MyScanner(this.log, System.in);
        String filename = sc.nextLine();
        log.out(filename);
        log.printAndLog("The log has been saved.");

    }

    public void setLogger(Logger log) {
        this.log = log;
    }
}

class Logger {
    List<String> list;

    public Logger() {
        this.list = new ArrayList<>();
    }

    public void append(String msg) {
        list.add(msg);
    }

    public void printAndLog(String msg) {
        System.out.println(msg);
        list.add(msg);
    }

    public void out(String path) {
        try(FileWriter fileWriter = new FileWriter(path)) {
            String str = "".join("\n", list);
            fileWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyScanner {
    Logger logger;
    Scanner sc;
    public MyScanner(Logger logger, InputStream in) {
        this.logger = logger;
        this.sc = new Scanner(in);
    }

    public MyScanner(Logger logger, File file) throws FileNotFoundException {
        this.logger = logger;
        this.sc = new Scanner(file);
    }

    public void useDelimiter(String delimiter) {
        sc.useDelimiter(delimiter);
    }

    public boolean hasNext() {
        return sc.hasNext();
    }

    public int nextInt() {
        int scannedInt = sc.nextInt();
        logger.append("" + scannedInt);
        return scannedInt;
    }

    public String next() {
        String scannedStr = sc.next();
        logger.append(scannedStr);
        return scannedStr;
    }

    public String nextLine() {
        String scannedLine = sc.nextLine();
        logger.append(scannedLine);
        return scannedLine;
    }
}

class MyInputStream extends InputStream{
    private static MyInputStream myInputStream = new MyInputStream();
    private MyInputStream() {}
    private Logger logger;
    private StringBuilder strLine = new StringBuilder();

    @Override
    public int read() throws IOException {
        int result = System.in.read();
        byte[] tmp = new byte[] {(byte)result};
        String resultStr = new String(tmp);
        strLine.append(resultStr);
        if(resultStr.contentEquals("\n")) {
            logger.append(strLine.toString());
            result = -1;
        }
        return result;
    }

    public static MyInputStream getInstance() {
        return myInputStream;
    }

    public void setLogger(Logger logger ) {
        this.logger = logger;
    }


}

public class Main {
    public static void main(String[] args) {
        CardDeckService cardDeckService = CardDeckService.getInstance();
        Logger logger = new Logger();
        cardDeckService.setLogger(logger);
        CardDeck cardDeck = new CardDeck();
        MyScanner sc = new MyScanner(logger, System.in);
        while(true) {
            logger.printAndLog("Input the action (add, remove, import, export, ask, exit" +
                    ", log, hardest card, reset stats)");
            String action = sc.nextLine();
            switch (action) {
                case "add":
                    cardDeckService.addCard(cardDeck);
                    break;
                case "remove":
                    cardDeckService.removeCard(cardDeck);
                    break;
                case "import":
                    cardDeckService.importDeck(cardDeck);
                    break;
                case "export":
                    cardDeckService.exportDeck(cardDeck);
                    break;
                case "ask":
                    cardDeckService.ask(cardDeck);
                    break;
                case "log":
                    cardDeckService.log(cardDeck);
                    break;
                case "hardest card":
                    cardDeckService.hardestCard(cardDeck);
                    break;
                case "reset stats":
                    cardDeckService.resetStats(cardDeck);
                    break;
                case "exit":
                    cardDeckService.exit();
                    return;
            }
        }

    }
}
