package expression;

public class Main {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);

        int ans = new Add(
                new Subtract(
                    new Multiply(
                            new Variable("x"),
                            new Variable("x")
                    ),
                    new Multiply(
                            new Const(2),
                            new Variable("x")
                    )
                ),
                new Const(1)).evaluate(x);
        System.out.println(ans);
    }
}
