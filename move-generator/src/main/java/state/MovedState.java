package state;

import api.IMovedState;
import colours.PlayerColour;
import move.Move;

public class MovedState implements IMovedState {

    private final IState delegate;
    private final Move move;

    public MovedState(IState delegate, Move move) {
        this.delegate = delegate;
        this.move = move;
    }

    public Move getMove() {
        return null;
    }

    public boolean isValid() {
        return false;
    }

    public PlayerColour getPlayerColour(int x, int y) {
        return delegate.getPlayerColour(x, y);
    }

    public char getChar(int x, int y) {
        return delegate.getChar(x, y);
    }
}
