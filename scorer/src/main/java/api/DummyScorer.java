package api;

import colours.PlayerColour;
import state.IState;

public class DummyScorer implements IStateScorer {
    public double scoreState(IState state, PlayerColour colour) {
        //Oh look a state, that's cool bro, have a random number
        return Math.random() * 100;
    }
}
