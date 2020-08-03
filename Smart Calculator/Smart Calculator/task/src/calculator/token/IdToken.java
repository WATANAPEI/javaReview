package calculator.token;

import calculator.token.Token;
import calculator.token.TokenType;

public class IdToken extends Token {

    public IdToken(String image) {
        this.image = image;
        this.type = TokenType.ID;
    }

}
