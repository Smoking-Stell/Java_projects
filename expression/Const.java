package expression;

import java.util.Objects;

public class Const implements GeneralExpression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int t = (int) value;
        return t;
    }

    @Override
    public int evaluate(int x) {
        int t = (int) value;
        return t;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Const) {
            return value == ((Const) object).getValue();
        }
        return false;
    }

    @Override
    public String toString() {
        if ((int) value != value)
            return Double.toString(value);
        return Integer.toString((int) value);
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isAssosiative() {
        return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
