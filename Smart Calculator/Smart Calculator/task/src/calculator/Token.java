package calculator;

/**
 * Token
 * Token type should be String(Operator) or Integer(Number)
 */
abstract class Token {
    TokenType type;
    String image;

    public String toString() {
        return image;
    }
}
