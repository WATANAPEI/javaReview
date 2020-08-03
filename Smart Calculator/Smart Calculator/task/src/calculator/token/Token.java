package calculator.token;

/**
 * Token
 * Token type should be String(Operator) or Integer(Number)
 */
public abstract class Token {
    public TokenType type;
    public String image;

    public String toString() {
        return image;
    }
}
