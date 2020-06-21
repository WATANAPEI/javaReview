package calculator;

import java.text.ParseException;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //read(scan) separate numbers with operators(by space)
        //aggregate operators to one operator use if statement for -
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            if(line.contentEquals("/help")) {
                help();
            } else if (line.contentEquals("/exit")) {
                exit();
                break; //terminate program here
            } else {
                Node node = new ExprNode();
                try {
                    node.parse(new Context(line)); //construct BST
                    CASTVisitor v = new CalcVisitor();
                    System.out.println(v.visit((ExprNode)node));
                    //System.out.println(node.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void help() {
        System.out.println(
                        "The program must calculate expressions like these: 4 + 6 - 8, 2 - 3 - 4," +
                        " and so on. It must support both unary and binary minus operators." +
                        " If the user has entered several operators following each other," +
                                " the program still should work (like Java or Python REPL).\n" +
                        "\n" +
                        "Consider that the even number of minuses gives a plus," +
                                " and the odd number of minuses gives a minus!" +
                                " Look at it this way: 2 -- 2 equals 2 - (-2) equals 2 + 2."
        );
    }

    public static void exit() {
        System.out.println("Bye!");
    }
}
