package api;

import move.Move;
import state.IState;

public interface IMovedState extends IState {

    /**
     *
     * @return The move object associated with this object
     */
    Move getMove();

}
