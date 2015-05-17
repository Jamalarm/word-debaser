package debaser;

import api.DummyScorer;
import colours.PlayerColour;
import state.IState;
import state.StateGenerationUtil;

import java.util.*;

public class DebaserService {

    public static void main(String[] args) {

        Collection<IState> states = StateGenerationUtil.generateRandomStates(100, 0.5);

        DummyScorer scorer = new DummyScorer();

        Map<Double, IState> scoresMap = new HashMap<Double, IState>(states.size());

        for (IState state : states) {
            double score = scorer.scoreState(state, PlayerColour.BLUE);
            scoresMap.put(score, state);
        }

        Set<Double> scores = scoresMap.keySet();

        Double highestScore = Collections.max(scores);

        System.out.println("highestScore = " + highestScore);
        System.out.println("State = \n" + scoresMap.get(highestScore));


    }

}
