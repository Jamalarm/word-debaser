package state;

import board.IBoard;
import colours.PlayerColour;

public class SimpleState implements IState {

    private final IBoard board;
    private final PlayerColour[][] colours;
    private final boolean isValid;

    public SimpleState(IBoard board, PlayerColour[][] colours) {
        this.board = board;
        this.colours = colours;
        isValid = true;
    }

    public PlayerColour getPlayerColour(int x, int y) {
        return null;
    }

    public boolean isValid() {
        return false;
    }

    public char getChar(int x, int y) {
        return board.getChar(x, y);
    }
}
