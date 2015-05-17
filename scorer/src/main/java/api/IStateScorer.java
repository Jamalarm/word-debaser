package api;

import colours.PlayerColour;
import state.IState;

public interface IStateScorer {

    /**
     * Scores the supplied state and returns a double representing the score
     * @param state The State object to be scored
     * @param colour The player perspective we are scoring from
     * @return A double representing the score of the object. 0 is the lowest possible score, there is
     * no upper limit
     */
    double scoreState(IState state, PlayerColour colour);

}
