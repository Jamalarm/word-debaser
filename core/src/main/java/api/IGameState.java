package api;

public interface IGameState {
    IBoard getBoard();

    PlayerColour getCellState(int x, int y);

    boolean isValid();
}
