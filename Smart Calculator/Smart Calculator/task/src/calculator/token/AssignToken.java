package calculator.token;

import calculator.token.Token;
import calculator.token.TokenType;

public class AssignToken extends Token {

    public AssignToken(String image) {
        this.image = image;
        this.type = TokenType.ASSIGN;
    }

}
