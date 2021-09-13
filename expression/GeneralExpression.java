package expression;

public interface GeneralExpression extends Expression, TripleExpression {
    int getPriority();
    boolean isAssosiative();
}
