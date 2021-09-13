package game;

public class Rhomb extends MNKBoard {
    public Rhomb(int n, int k) {
        super(n * 2 - 1, n * 2 - 1, k);
        int t = 2 * n - 2;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                cells[i][j] = Cell.Un;
                cells[t - i][t - j] = Cell.Un;
                cells[t - i][j] = Cell.Un;
                cells[i][t - j] = Cell.Un;
                freeCells -= 4;
            }
        }
    }
}
