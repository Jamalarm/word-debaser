package strategies;

import api.IMovedState;

import java.util.concurrent.Callable;

public interface IMoveGenerationStrategy extends Callable<IMovedState> {
}
