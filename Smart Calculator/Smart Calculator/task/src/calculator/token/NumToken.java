package calculator.token;

public class NumToken extends Token {
    private Integer value;
    public NumToken(Integer value) {
        this.value = value;
        this.type = TokenType.NUM;
        this.image = value.toString();
    }
    public Integer getValue() {
        return this.value;
    }
}
