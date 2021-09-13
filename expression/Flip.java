package expression;

public class Flip extends AbstractUnoOperation {
    public Flip(GeneralExpression element) {
        super(element);
    }

    @Override
    protected int calculate(int x) {
        long t = Integer.toUnsignedLong(Integer.reverse(x));
        return t == 0 ? 0 : (int) (t/ Long.lowestOneBit(t));
    }

    @Override
    protected String sign() {
        return "flip";
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
