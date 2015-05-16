package state;

import board.IBoard;
import colours.PlayerColour;

public interface IGameState {
    IBoard getBoard();

    PlayerColour getCellState(int x, int y);

    boolean isValid();
}
