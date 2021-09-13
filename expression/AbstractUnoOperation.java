package expression;

import java.util.Objects;

public abstract class AbstractUnoOperation implements GeneralExpression{
    private final GeneralExpression element;

    public AbstractUnoOperation (GeneralExpression element) {
        this.element = element;
    }

    protected abstract int calculate(int x);
    protected abstract String sign();

    @Override
    public int evaluate(int x) {
        return calculate(element.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(element.evaluate(x, y, z));
    }

    private String wrap(GeneralExpression x, boolean needBackets) {
        return needBackets ? "(" + x.toMiniString() + ")" : x.toMiniString();
    }

    @Override
    public String toMiniString() {
        return " " + sign() + " " +
                wrap(element, (element.getPriority() < this.getPriority()) ||
                        (element.getPriority() == this.getPriority() && (element.isAssosiative() || this.isAssosiative())));
    }

    public String toString() {
        return sign() + "(" + element + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object.getClass() == getClass()) {
            AbstractUnoOperation t = (AbstractUnoOperation) object;
            return element.equals(t.element);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, this.getClass());
    }
}
