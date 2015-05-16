package state;

import api.IMovedState;
import colours.PlayerColour;
import move.Move;

import java.util.Set;

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

        //TODO add some updating code here to modify the state

        return builder.build();
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
}
