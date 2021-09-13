package game;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    private int checkIn() {
        while (!in.hasNextInt()){
            System.out.println("Insert is not a numbers");
            in.next();
        }
        return in.nextInt();
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            try {
                final Move move = new Move(checkIn() - 1, checkIn() - 1, cell);

                if (position.isValid(move)) {
                    return move;
                }

                final int row = move.getRow();
                final int column = move.getColumn();
                out.println("Move " + move + " is invalid");
            } catch (InputMismatchException e) {
                System.out.println("Insert is not a numbers" + e.getMessage());
            }
        }
    }
}
