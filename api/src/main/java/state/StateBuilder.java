package state;

import board.IBoard;
import colours.PlayerColour;

public class StateBuilder {

    private IBoard board;
    private PlayerColour[][] colours;

    public StateBuilder() {
    }

    public void setBoard(IBoard board) {
        this.board = board;
    }

    public void setColours(PlayerColour[][] colours) {
        this.colours = colours;
    }

    public void setColour(PlayerColour colour, int x, int y) {
        colours[x][y] = colour;
    }

    public IState build() {
        return new SimpleState(board, colours);
    }

}
