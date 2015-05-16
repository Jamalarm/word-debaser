package api;

import colours.PlayerColour;
import state.IGameState;

import java.util.Collection;

public interface IMoveGenerator {

    Collection<IGameState> getPossibleMoveStates(IGameState state, PlayerColour player);

}
