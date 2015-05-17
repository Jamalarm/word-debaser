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

        //Get the Strategies (one per starting point)
        Collection<IMoveGenerationStrategy> strategies = buildStrategies(state, player);

        try {
            //Call will invoke all strategies on the thread pool and wait for return - this is where the multithreading happens
            List<Future<IMovedState>> futures = executor.invokeAll(strategies);

            Collection<IMovedState> movedStates = new HashSet<IMovedState>(futures.size());

            //Gets the actual MoveState object from the futures
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

    /**
     * Products a Move generation strategy object for every player-coloured cell in the IState
     * @param state The State to generate moves for
     * @param player The player we are generating moves for
     * @return 1 Strategy object per player coloured cell
     */
    private Collection<IMoveGenerationStrategy> buildStrategies(IState state, PlayerColour player) {
        Collection<IMoveGenerationStrategy> strategies = new ArrayList<IMoveGenerationStrategy>();

        for (int[] coords : state.getPlayerCoords(player)) {
            strategies.add(MoveGenerationStrategyFactory.getBasicStrategy(state, player, coords[0], coords[1]));
        }

        return strategies;
    }
}
