package expression;

import java.util.Objects;

public abstract class AbstractBinOperation implements GeneralExpression{
    private final GeneralExpression first, second;

    public AbstractBinOperation (GeneralExpression first, GeneralExpression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract int calculate(int x, int y);
    protected abstract String sign();

    @Override
    public int evaluate(int x) {
        return calculate(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    private String wrap(GeneralExpression x, boolean needBackets) {
        return needBackets ? "(" + x.toMiniString() + ")" : x.toMiniString();
    }

    @Override
    public String toMiniString() {
        return wrap(first, first.getPriority() < this.getPriority()) +
                " " + sign() + " " +
                wrap(second, (second.getPriority() < this.getPriority()) ||
                        (second.getPriority() == this.getPriority() && (second.isAssosiative() || this.isAssosiative())));
    }

    public String toString() {
        return "(" + first + " " + sign() + " " + second + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object.getClass() == getClass()) {
            AbstractBinOperation t = (AbstractBinOperation) object;
            return first.equals(t.first) && second.equals(t.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, this.getClass());
    }
}
