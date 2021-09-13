package expression.parser;

import expression.*;
import java.util.Set;

public class ExpressionParser extends BaseParser implements Parser {
    private final int lowPriority = -3;
    private final int highPriority = 3;
    private final Set <String> unoOperations = Set.of("flip", "low");

    protected ExpressionParser() {
        super();
    }

    @Override
    public TripleExpression parse(String expression) {
        setSource(new StringSource(expression));
        nextChar();
        return parsePriority(lowPriority);
    }

    public GeneralExpression parsePriority(int priority) {
        skipWhitespace();

        if (priority == highPriority) {
            return parseNew();
        }

        GeneralExpression ready = parsePriority(priority + 1);

        while(true) {
            skipWhitespace();
            final Operation t = Operation.OPERANDS.get(ch);
            if (t == null || Operation.PRIORITIES.get(t) != priority) {
                return ready;
            }

            nextChar();
            ready = makeBinOperation(t, ready, parsePriority(priority + 1));
        }
    }

    private GeneralExpression parseNew() {
        //System.out.println(ch);
        if (test('(')) {
            skipWhitespace();
            GeneralExpression ready = parsePriority(lowPriority);
            skipWhitespace();
            expect(')');
            return ready;
        } else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new UnaryMinus(parseNew());
        } else if (between('0', '9')) {
            return parseConst(true);
        } else {
            String variable = lookForVariable();
            if (unoOperations.contains(variable)) {
                return makeUnoOperation(variable, parseNew());
            }
            return new Variable(variable);
        }
    }

    private GeneralExpression makeBinOperation(Operation oper, GeneralExpression left, GeneralExpression right) {
        return switch (oper) {
            case ADD -> new Add(left, right);
            case SUB -> new Subtract(left, right);
            case MUL -> new Multiply(left, right);
            case DIV -> new Divide(left, right);
            case CNJ -> new Conjunction(left, right);
            case DIS -> new Disjunction(left, right);
            case XOR -> new ModTwo(left, right);
        };
    }

    private GeneralExpression makeUnoOperation(String oper, GeneralExpression element) {
       return switch (oper) {
            case "low" -> new Low(element);
            case "flip" -> new Flip(element);
            default -> null;
        };
    }

    private GeneralExpression parseConst(boolean mark) {
        final StringBuilder sb = new StringBuilder();
        if (!mark) {
            sb.append('-');
        }
        skipWhitespace();
        parseNumber(sb);

        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw error("Something wrong: " + sb);
        }
    }

    private void parseNumber(StringBuilder sb) {

        if (test('-')) {
            sb.append('-');
        }
        if (test('0')) {
            sb.append('0');
            return;
        }
        while (between('0', '9')) {
            sb.append(ch);
            nextChar();
        }
    }

    private String lookForVariable() {
        skipWhitespace();
        StringBuilder ans = new StringBuilder();
        while (Character.isLetter(ch)) {
            ans.append(ch);
            nextChar();
        }
        skipWhitespace();
        return ans.toString();
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }
}