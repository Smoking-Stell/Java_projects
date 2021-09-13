package expression;

public class UnaryMinus extends AbstractUnoOperation{

    public UnaryMinus(GeneralExpression element) {
        super(element);
    }

    @Override
    protected int calculate(int x) {
        return -x;
    }

    @Override
    protected String sign() {
        return "-";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isAssosiative() {
        return false;
    }
}
