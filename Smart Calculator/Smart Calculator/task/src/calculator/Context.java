package calculator;

import java.text.ParseException;
import java.util.Scanner;

class Context {
    private Scanner sc;
    private String currentToken;

    public Context(String text) {
        sc = new Scanner(text);
        currentToken = nextToken();
    }

    public String nextToken() {
        if (sc.hasNext()) {
            currentToken = sc.next();
        } else {
            currentToken = null;
        }
        return currentToken;
    }

    public void skipToken(String expectedToken) throws ParseException {
        if (!currentToken.contentEquals(expectedToken)) {
            throw new ParseException("Unexpected Token: " + currentToken, 0);
        }
        nextToken();
    }

    public String getCurrentToken() {
        return this.currentToken;
    }

    public Integer getCurrentNumber() throws  ParseException{
        Integer number = 0;
        try {
            number = Integer.parseInt(this.currentToken);
        } catch(NumberFormatException e) {
            throw new ParseException("Error: " + e, 0);
        }
        return number;
    }
}
