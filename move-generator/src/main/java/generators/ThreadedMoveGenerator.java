package generators;

import api.IMoveGenerator;
import api.IMovedState;
import colours.PlayerColour;
import state.IState;
import strategies.IMoveGenerationStrategy;
import strategies.MoveGenerationStrategyFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ThreadedMoveGenerator implements IMoveGenerator {

    private final ExecutorService executor;

    public ThreadedMoveGenerator(ExecutorService executor) {
        this.executor = executor;
    }


    public Collection<IMovedState> getPossibleMoveStates(IState state, PlayerColour player) {

        Collection<IMoveGenerationStrategy> strategies = buildStrategies(state, player);

        try {
            //Call will invoke all strategies on the thread pool and wait for return - this is where the multithreading happens
            List<Future<IMovedState>> futures = executor.invokeAll(strategies);

            Collection<IMovedState> movedStates = new HashSet<IMovedState>(futures.size());

            for (Future<IMovedState> future : futures) {
                movedStates.add(future.get());
            }

            return movedStates;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            //Should never be hit
            e.printStackTrace();
            return null;
        }
    }

    private Collection<IMoveGenerationStrategy> buildStrategies(IState state, PlayerColour player) {
        Collection<IMoveGenerationStrategy> strategies = new ArrayList<IMoveGenerationStrategy>();

        for (int[] coords : state.getPlayerCoords(player)) {
            strategies.add(MoveGenerationStrategyFactory.getBasicStrategy(state, player, coords[0], coords[1]));
        }

        return strategies;
    }
}
