package state;

import board.IBoard;
import colours.PlayerColour;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        return colours[x][y];
    }

    public boolean isValid() {
        return false;
    }

    public Set<int[]> getPlayerCoords(PlayerColour colour) {
        Set<int[]> playerCoords = new HashSet<int[]>();
        for (int x = 0; x < colours.length; x++) {
            for (int y = 0; y < colours[0].length; y++) {
                if (colours[x][y].equals(colour)) {
                    playerCoords.add(new int[]{x, y});
                }
            }
        }
        return playerCoords;
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

    public StateBuilder toBuilder() {
        StateBuilder builder = new StateBuilder();
        builder.setBoard(board);

        //Defensive copy
        PlayerColour[][] coloursCopy = new PlayerColour[colours.length][];
        for (int i = 0; i < colours.length; i++) {
            coloursCopy[i] = Arrays.copyOf(colours[i], colours[i].length);
        }

        builder.setColours(coloursCopy);
        return builder;
    }
}
