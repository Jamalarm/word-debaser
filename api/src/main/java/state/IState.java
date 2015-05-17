package state;

import board.IBoard;
import colours.PlayerColour;

import java.util.Set;

/**
 * State objects are SELF VERIFYING. On construction, any implementation should check that the supplied game
 * state is VALID and reject object construction if not
 */
public interface IState extends IBoard {

    /**
     * Query the PlayerColour state of the board
     * @param x index between 0 and BOARD_X
     * @param y index between 0 and BOARD_Y
     * @return the PlayerColour of the specified cell
     */
    PlayerColour getPlayerColour(int x, int y);

    /**
     * Returns a set of coordinates {x,y} representing all the cells that have the specified player colour
     * @param colour The player colour we want to look for
     * @return A set of coordinates {x,y}
     */
    Set<int[]> getPlayerCoords(PlayerColour colour);

    /**
     * The state class is immutable, to modify this object you must first get the builder and then create a new object
     * @return A statebuilder representation of the current state
     */
    StateBuilder toBuilder();

}
