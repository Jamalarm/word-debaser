package api;

import state.IState;

public interface IStateScorer {

    /**
     * Scores the supplied state and returns a double representing the score
     * @param state The State object to be scored
     * @return A double representing the score of the object. 0 is the lowest possible score, there is
     * no upper limit
     */
    double scoreState(IState state);

}
