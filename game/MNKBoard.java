package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.Un, ' '
    );

    protected Cell[][] cells;
    private Cell turn;
    private int rowsCount;
    private int columnsCount;
    private int k;
    protected int freeCells;
    private boolean continueTurn;

    public MNKBoard(int n, int m, int k) {
        rowsCount = n;
        columnsCount = m;
        freeCells = n * m;
        this.k = k;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        continueTurn = true;
    }

    private boolean exist(int r, int c) {
        return !(r < 0 || r >= rowsCount || c < 0 || c >= columnsCount);
    }

    private int directCheck (int r, int c, int i, int j) {
        int t = 0;
        while (exist(r, c) && cells[r][c] == turn) {
            r += i;
            c += j;
            t++;
        }
        return t;
    }

    private int fullCheck(int r, int c) {
        if (!exist(r, c)) {
            return 0;
        }
        int col = directCheck(r, c, 1, 0) + directCheck(r, c, -1, 0) - 1;
        int row = directCheck(r, c, 0, 1) + directCheck(r, c, 0, -1) - 1;
        int diagMain = directCheck(r, c, 1, 1) + directCheck(r, c, -1, -1) - 1;
        int diagSec = directCheck(r, c, -1, 1) + directCheck(r, c, 1, -1) - 1;
        return Math.max(Math.max(col, row), Math.max(diagMain, diagSec));
    }

    @Override
    public Position getPosition() {
        return new LocalPosition(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        freeCells--;

        int momentAns = fullCheck(move.getRow(), move.getColumn());

        if (momentAns >= k) {
            return Result.WIN;
        }
        if (freeCells == 0) {
            return Result.DRAW;
        }

        continueTurn = false;
        if (momentAns < 4) {
            turn = turn == Cell.X ? Cell.O : Cell.X;
        } else {
            continueTurn = true;
        }
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        if (move == null) {
            return false;
        }
        int r = move.getRow();
        int c = move.getColumn();
        return exist(r, c)
                && cells[r][c] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public int getN() {
        return rowsCount;
    }

    @Override
    public int getM() {
        return columnsCount;
    }

    @Override
    public int getK() {
        return k;
    }

    public boolean isContinue() {
        return continueTurn;
    }

    public void setTurn() {
        continueTurn = true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int c = 0; c < columnsCount; c++) {
            sb.append(c + 1);
        }
        for (int r = 0; r < rowsCount; r++) {
            sb.append("\n");
            sb.append(r + 1);
            for (int c = 0; c < columnsCount; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
