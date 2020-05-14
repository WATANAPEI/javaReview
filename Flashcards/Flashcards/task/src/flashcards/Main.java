package flashcards;
import java.awt.desktop.SystemSleepEvent;
import java.io.*;
import java.util.*;

class CardDeck {

    private HashMap<String, String> deck;

    public CardDeck() {
        this.deck = new LinkedHashMap<>();
    }

    public void add(String key, String value) {
        this.deck.put(key, value);
    }

    public void remove(String key) throws NoCardFoundException {
        if (!deck.containsKey(key)) {
            throw new NoCardFoundException("Can't remove \"" + key + "\": there is no such card.");
        } else {
            deck.remove(key);
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

    public Map.Entry<String, String> getEntry() {
        return (Map.Entry<String, String>) this.deck.entrySet();
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
        cardDeck.add(term, def);
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
                try {
                    if(cardDeck.containsKey(term)) {
                        cardDeck.remove(term);
                    }
                    cardDeck.add(term, def);
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
                printWriter.println(term + "," + cardDeck.get(term));
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
            cardDeck.remove(key);
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
            } else if (cardDeck.containsValue(ans)) {
                StringBuilder def = new StringBuilder();
                for(String k: cardDeck.keySet()) {
                    if (cardDeck.get(k).equals(ans)) {
                        def.append(k);
                    }
                }
                System.out.println("Wrong answer. The correct one is \"" + cardDeck.get(randomQuestionTerm)
                        + "\", you've just written the definition of \"" + def + "\".");

            } else {
                System.out.println("Wrong answer. The correct one is \"" + cardDeck.get(randomQuestionTerm) + "\".");
            }
            asked++;
            randomNum = rand.nextInt(questions.size());

            if(asked == askTime) {
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardDeckService cardDeckService = CardDeckService.getInstance();
        CardDeck cardDeck = new CardDeck();
        while(true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit)");
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
                case "exit":
                    cardDeckService.exit();
                    return;
            }
        }

    }
}
