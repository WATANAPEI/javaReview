package calculator.token;

import calculator.OpType;

import java.text.ParseException;

public class OpToken extends Token {
    private OpType translatedOp;

    public OpToken(String value) {
        this.type = TokenType.OP;
        this.image = value;
        this.translatedOp = translate(value);
    }
    OpType translate(String value) {
        OpType operator = OpType.ADD;
        for(var e: value.split("")) {
            if(e.contentEquals("+")) {
                operator = OpType.ADD;
            } else if (e.contentEquals("-")) {
                if(operator == OpType.SUB) {
                    operator = OpType.ADD;
                }else {
                    operator = OpType.SUB;
                }
            } else {
                new ParseException("Parse Error at OperatorNode", 0);
            }
        }
        return operator;
    }
    public OpType getValue() {
        return this.translatedOp;
    }

}
