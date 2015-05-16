package state;

import board.IBoard;
import colours.PlayerColour;

import static board.SimpleBoard.SEPARATOR;

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

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int y = BOARD_Y - 1; y >= 0; y--) {
            out.append(SEPARATOR);
            for (int x = 0; x < BOARD_X; x++) {
                out.append(colours[x][y].getColourCode());
                out.append(board.getChar(x, y));
                out.append(SEPARATOR);
            }
            out.append("\n");
        }

        return out.toString();
    }
}
