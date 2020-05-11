package flashcards;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static String getCardString(Map<String, String> card) {
           Scanner sc = new Scanner(System.in);
           System.out.println("The card #" + (card.size() +1) + ":");
           while(true)
           {
               String input = sc.nextLine();
               if(card.containsKey(input)) {
                   System.out.println("The card \"" + input + "\" already exists. Try again:");
               } else {
                   return input;
               }
           }
    }

    public static String getDefString(Map<String, String> card) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The definition of the card #" + (card.size()+1) + ":");
        while(true)
        {
            String input = sc.nextLine();
            if(card.containsValue(input)) {
                System.out.println("The definition \"" + input + "\" already exists. Try again:");
            } else {
                return input;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Input the number of cards:");
        Scanner sc = new Scanner(System.in);
        String cards = sc.nextLine();
        int cardNum = Integer.parseInt(cards);
        LinkedHashMap<String, String> card = new LinkedHashMap<>();
        for(int i = 0; i < cardNum; i++) {
            String key = getCardString(card);
            String val = getDefString(card);
            card.put(key, val);
        }
        int i = 0;
        for(Map.Entry<String, String> e : card.entrySet()) {
            System.out.println("Print the definition of \"" + e.getKey() + "\":");
            String ans = sc.nextLine();
            if(ans.equals(e.getValue())) {
                System.out.println("Correct answer.");
            } else if (card.containsValue(ans)) {
                StringBuilder def = new StringBuilder();
                for(String k: card.keySet()) {
                    if (card.get(k).equals(ans)) {
                        def.append(k);
                    }
                }
                System.out.println("Wrong answer. The correct one is \"" + e.getValue()
                        + "\", you've just written the definition of \"" + def + "\".");

            } else {
                System.out.println("Wrong answer. The correct one is \"" + e.getValue() + "\".");
            }
        }
    }
}
