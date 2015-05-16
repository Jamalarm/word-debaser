package state;

import board.IBoard;
import colours.PlayerColour;

import java.util.Set;

public interface IState extends IBoard {

    /**
     * Query the PlayerColour state of the board
     * @param x index between 0 and BOARD_X
     * @param y index between 0 and BOARD_Y
     * @return the PlayerColour of the specified cell
     */
    PlayerColour getPlayerColour(int x, int y);

    /**
     * Checks if the state represented in this object is legal
     * @return true if the game state is legal
     */
    boolean isValid();

    Set<int[]> getPlayerCoords(PlayerColour colour);

    StateBuilder toBuilder();

}
