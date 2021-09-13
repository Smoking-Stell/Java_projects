package expression;

public class Subtract extends AbstractBinOperation {
    public Subtract(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) {
        return x - y;
    }

    @Override
    protected String sign() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isAssosiative() {
        return true;
    }
}
