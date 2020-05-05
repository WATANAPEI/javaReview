package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        String cards = scanner.nextLine();
        int cardNum = Integer.parseInt(cards);
        String[] terms = new String[cardNum];
        String[] definitions = new String[cardNum];
        for(int i = 0; i < cardNum; i++) {
            System.out.println("The card #" + (i+1) + ":");
            terms[i] = scanner.nextLine();
            System.out.println("The definition of the card #" + (i+1) + ":");
            definitions[i] = scanner.nextLine();
        }
        for(int i = 0; i < cardNum; i++) {
            System.out.println("Print the definition of \"" + terms[i] + "\":");
            String ans = scanner.nextLine();
            if(ans.equals(definitions[i])) {
                System.out.println("Correct answer.");
            } else {
                System.out.println("Wrong answer. The correct one is \"" + definitions[i] + "\".");

            }

        }
    }
}
