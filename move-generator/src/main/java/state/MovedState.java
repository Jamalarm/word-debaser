package state;

import api.IMovedState;
import colours.PlayerColour;
import move.Move;
import verifiers.RecursiveVerifier;

import java.util.Set;

import static board.SimpleBoard.SEPARATOR;

public class MovedState implements IMovedState {

    private final Move move;
    private final IState delegate;

    public MovedState(IState originalState, Move move) {
        this.move = move;
        this.delegate = updateState(originalState, move);
    }

    private IState updateState(IState originalState, Move move) {
        StateBuilder builder = originalState.toBuilder();

        for (int[] coords : move.getPath()) {
            builder.setColour(move.getColour(), coords[0], coords[1]);
        }

        IState tempState = builder.build();
        //FIXME State is built twice here
        return RecursiveVerifier.makeStateValid(tempState);
    }

    public Move getMove() {
        return move;
    }

    public PlayerColour getPlayerColour(int x, int y) {
        return delegate.getPlayerColour(x, y);
    }

    public boolean isValid() {
        return false;
    }

    public Set<int[]> getPlayerCoords(PlayerColour colour) {
        return delegate.getPlayerCoords(colour);
    }

    public char getChar(int x, int y) {
        return delegate.getChar(x, y);
    }

    public StateBuilder toBuilder() {
        return delegate.toBuilder();
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int y = BOARD_Y - 1; y >= 0; y--) {
            out.append(SEPARATOR);
            for (int x = 0; x < BOARD_X; x++) {
                out.append(delegate.getPlayerColour(x, y).getASCIIFormatCode());
                out.append(delegate.getChar(x, y));
                out.append(SEPARATOR);
            }
            out.append("\n");
        }

        return out.toString();
    }
}
