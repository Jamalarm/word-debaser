package api;

import colours.PlayerColour;
import state.IState;

import java.util.Collection;

public interface IMoveGenerator {

    Collection<IMovedState> getPossibleMoveStates(IState state, PlayerColour player);

}
