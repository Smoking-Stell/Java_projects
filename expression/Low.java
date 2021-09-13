package expression;

public class Low extends AbstractUnoOperation {
    public Low(GeneralExpression element) {
        super(element);
    }

    @Override
    protected int calculate(int x) {
        return Integer.lowestOneBit(x);
    }

    @Override
    protected String sign() {
        return "low";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isAssosiative() {
        return true;
    }
}
