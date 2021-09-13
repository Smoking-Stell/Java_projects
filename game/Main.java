package game;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            int n = Integer.parseInt(args[0]);
            //int m = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[1]);

            if (n == 0 || k == 0) {
                throw new IOException("Empty insert");
            }

            final Game game = new Game(true, new HumanPlayer(), new RandomPlayer());
            int result;
            do {
                Board board = new Rhomb(n, k);
                result = game.play(board);
                System.out.println("Game result: " + result);
            } while (result != 0);
        } catch (NumberFormatException e) {
            System.out.println("Insert wrong numbers " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Wrong is: " + e.getMessage());
        }

    }
}
