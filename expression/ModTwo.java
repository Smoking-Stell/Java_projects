package expression;

public class ModTwo extends AbstractBinOperation {
    public ModTwo(GeneralExpression first, GeneralExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int x, int y) {
        return x ^ y;
    }

    @Override
    protected String sign() {
        return "^";
    }

    @Override
    public int getPriority() {
        return  -2;
    }

    @Override
    public boolean isAssosiative() {
        return true;
    }
}
