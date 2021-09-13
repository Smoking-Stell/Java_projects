package expression;

public class Multiply extends AbstractBinOperation {
    public Multiply(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) {
        return x * y;
    }

    @Override
    protected String sign() {
        return "*";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isAssosiative() {
        return false;
    }
}
