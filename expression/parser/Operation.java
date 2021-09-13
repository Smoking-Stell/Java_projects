package expression.parser;

import java.util.Map;

public enum Operation {
    DIS, CNJ, XOR,
    ADD, SUB,
    MUL, DIV;

    public static final Map<Character, Operation> OPERANDS = Map.of(
            '&', CNJ,
            '|', DIS,
            '^', XOR,
            '+', ADD,
            '-', SUB,
            '*', MUL,
            '/', DIV
    );

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            CNJ, -1,
            DIS, -3,
            XOR, -2,
            ADD, 0,
            SUB, 0,
            MUL, 1,
            DIV, 1
    );
}
