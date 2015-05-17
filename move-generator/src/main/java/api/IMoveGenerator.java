package api;

import colours.PlayerColour;
import state.IState;

import java.util.Collection;

public interface IMoveGenerator {

    /**
     * Generates some list of valid MovedStates given the inputs
     * @param state The starting state from which the moves will be made
     * @param player The player that we are generating moves for
     * @return A collection of moved states representing some set of possible moves
     */
    Collection<IMovedState> getPossibleMoveStates(IState state, PlayerColour player);

}
