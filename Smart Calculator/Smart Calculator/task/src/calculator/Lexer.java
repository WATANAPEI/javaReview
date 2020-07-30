package calculator;
/**
 * Lexer
 */
class Lexer {
    private PeekableScanner sc;

    Lexer(String line) {
        sc = new PeekableScanner(line);
    }

    Token nextToken() {
        if (sc.hasNext()) {
            String nextStr = sc.next();
            return dispatchToken(nextStr);
        } else {
            return null;
        }
    }

    Token peekNextToken() {
        String nextTokenString = sc.peek();
        if(nextTokenString != null) {
            return dispatchToken(nextTokenString);
        } else {
            return null;
        }
    }

    Token dispatchToken(String tokenString) {
        if (tokenString.matches("[\\+-]?\\d+")) {
            return new NumToken(Integer.parseInt(tokenString));
        } else if (tokenString.matches("\\w+")) {
            return new IdToken(tokenString);
        } else if (tokenString.matches("=")) {
            return new AssignToken(tokenString);
        } else {
            return new OpToken(tokenString);
        }
    }
}
