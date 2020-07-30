package calculator;

class NumToken extends Token {
    private Integer value;
    NumToken(Integer value) {
        this.value = value;
        this.type = TokenType.NUM;
        this.image = value.toString();
    }
    public Integer getValue() {
        return this.value;
    }
}
