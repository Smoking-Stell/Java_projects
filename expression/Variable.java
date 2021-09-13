package expression;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Variable implements GeneralExpression {
    private final String value;
    public Variable(String in) {
        this.value = in;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z)  {
        switch (value) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new IllegalArgumentException("Not a numbers");
    }

    @Override
    public boolean isAssosiative() {
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Variable) {
            return value.equals(((Variable) object).getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
