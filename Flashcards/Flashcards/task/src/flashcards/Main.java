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

    private CardDeckService() {
    }

    public static CardDeckService getInstance() {
        return new CardDeckService();
    }

    public void addCard(CardDeck cardDeck) {
        String term = null;
        String def = null;
        try {
            term = _getCardString(cardDeck);
            def = _getDefString(cardDeck);
        } catch (TermExistException e) {
            System.out.println(e.getMessage());
            return;
        } catch (DefinitionExistException e) {
            System.out.println(e.getMessage());
            return;
        }
        cardDeck.addWithWrongCount(term, def, 0);
        System.out.println("The pair {\"" + term + "\":\"" + def + "\"} has been added.");
        System.out.println("");
    }

    private String _getCardString(CardDeck cardDeck) throws TermExistException {
        Scanner sc = new Scanner(System.in);
        System.out.println("The card:");
        String input = sc.nextLine();
        if(cardDeck.containsKey(input)) {
            throw new TermExistException("The card \"" + input + "\" already exists.");
        } else {
            return input;
        }
    }

    private String _getDefString(CardDeck cardDeck) throws DefinitionExistException{
        Scanner sc = new Scanner(System.in);
        System.out.println("The definition of the card:");
        String input = sc.nextLine();
        if(cardDeck.containsValue(input)) {
            throw new DefinitionExistException("The definition \"" + input + "\" already exists.");
        } else {
            return input;
        }
    }

    public void importDeck(CardDeck cardDeck) {
        System.out.println("File name:");
        Scanner sc = new Scanner(System.in);
        String  path = sc.nextLine();
        File file = new File(path);
        try {
            Scanner scFile = new Scanner(file);
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
            System.out.println(count + " cards have been loaded");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void exportDeck(CardDeck cardDeck) {
        System.out.println("File name:");
        Scanner sc = new Scanner(System.in);
        File file = new File(sc.nextLine());
        try(PrintWriter printWriter = new PrintWriter(file)) {
            int count = 0;
            for(String term: cardDeck.keySet()) {
                printWriter.println(term + "," + cardDeck.get(term) + "," + cardDeck.wrongCount(term));
                ++count;
            }
            System.out.println(count + " cards have been saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCard(CardDeck cardDeck) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The card:");
        String key = sc.nextLine();
        try {
            cardDeck.removeWithWrongCount(key);
            System.out.println("The card has been removed.");
        } catch (NoCardFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exit() {
        System.out.println("Bye bye!");
    }

    public void ask(CardDeck cardDeck) {
        System.out.println("How many times to ask?");
        Scanner sc = new Scanner(System.in);
        int askTime = sc.nextInt(); sc.nextLine();
        int asked = 0;
        List<String> questions = new ArrayList<String>(cardDeck.keySet());
        Random rand = new Random();
        int randomNum = rand.nextInt(questions.size());

        while(true) {
            String randomQuestionTerm = questions.get(randomNum);
            System.out.println("Print the definition of \"" + randomQuestionTerm + "\":");
            String ans = sc.nextLine();
            if(ans.equals(cardDeck.get(randomQuestionTerm))) {
                System.out.println("Correct answer.");
            } else {
                cardDeck.wrongAnswer(randomQuestionTerm, 1);
                if (cardDeck.containsValue(ans)) {
                    StringBuilder def = new StringBuilder();
                    for (String k : cardDeck.keySet()) {
                        if (cardDeck.get(k).equals(ans)) {
                            def.append(k);
                        }
                    }
                    System.out.println("Wrong answer. The correct one is \"" + cardDeck.get(randomQuestionTerm)
                            + "\", you've just written the definition of \"" + def + "\".");

                } else {
                    System.out.println("Wrong answer. The correct one is \"" + cardDeck.get(randomQuestionTerm) + "\".");
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
            System.out.println("There are no cards with errors.");
        } else {
            if (hardestTerm.size() == 1) {
                System.out.println("The hardest card is \"" + hardestTerm.get(0) +
            "\". You have " + hardest + " errors answering it.");
            } else {
                System.out.print("The hardest cards are ");
                for(int i = 0; i < hardestTerm.size()-1; i++) {
                    System.out.println("\"" + hardestTerm.get(i) + "\"");
                    if(i != hardestTerm.size()-1) {
                        System.out.println(", ");
                    } else {
                        System.out.println(". ");
                    }
                }
                System.out.println("You have " + hardest + " errors answering them.");
            }
        }

    }

    public void resetStats(CardDeck cardDeck) {
        cardDeck.resetWrongCount();
        System.out.println("Card statistics has been reset.");
    }

    public void log(CardDeck cardDeck) {
        String msg = "File name:";
        log.printAndLog(msg);

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
            for(var str: list) {
                fileWriter.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardDeckService cardDeckService = CardDeckService.getInstance();
        Logger log = new Logger();
        cardDeckService.setLogger(log);
        CardDeck cardDeck = new CardDeck();
        while(true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit" +
                    ", log, hardest card, reset status)");
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
                case "reset status":
                    cardDeckService.resetStats(cardDeck);
                    break;
                case "exit":
                    cardDeckService.exit();
                    return;
            }
        }

    }
}
