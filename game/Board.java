package game;

public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);
    boolean isContinue();
    void setTurn();
}