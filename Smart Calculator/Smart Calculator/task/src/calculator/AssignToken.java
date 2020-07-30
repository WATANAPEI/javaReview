package calculator;

class AssignToken extends Token {

    public AssignToken(String image) {
        this.image = image;
        this.type = TokenType.ASSIGN;
    }

}
