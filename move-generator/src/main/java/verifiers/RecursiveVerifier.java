package verifiers;

import colours.PlayerColour;
import state.IState;
import state.StateBuilder;

import java.util.HashSet;
import java.util.Set;

import static board.IBoard.BOARD_X;
import static board.IBoard.BOARD_Y;
import static colours.PlayerColour.*;

public class RecursiveVerifier {

    public static IState makeStateValid(IState state) {

        StateBuilder builder = state.toBuilder();

        //Check blue cells
        for (int[] coord : state.getPlayerCoords(BLUE)) {
            int x = coord[0];
            int y = coord[1];
            if (!isCoordValid(state, x, y, BLUE, new HashSet<int[]>())) {
                builder.setColour(NONE, x, y);
            }
        }

        //Check orange cells
        for (int[] coord : state.getPlayerCoords(ORANGE)) {
            int x = coord[0];
            int y = coord[1];
            if (!isCoordValid(state, x, y, ORANGE, new HashSet<int[]>())) {
                builder.setColour(NONE, x, y);
            }
        }

        return builder.build();
    }

    //TODO this method can be optimised - make it first checks if the next cell has been previously verified
    //use a chain of trust model
    private static boolean isCoordValid(IState state, int x, int y, PlayerColour playerColour, Set<int[]> pastPath) {
        //Check if we are in a non-friendly cell
        if (!state.getPlayerColour(x, y).equals(playerColour)) {
            return false;
        }

        //Check if we have been here before
        for (int[] coord : pastPath) {
            if (x == coord[0] && y == coord[1]) {
                return false;
            }
        }

        //Check if we are in a home row
        if (playerColour.equals(BLUE) && y == 0) {
            return true;
        }
        if (playerColour.equals(ORANGE) && y == BOARD_Y - 1) {
            return true;
        }

        //Recursively check surrounding cells
        //First add current cell to path to avoid infinite looping
        pastPath.add(new int[]{x, y});

        //Check straight down
        if (y > 0 && isCoordValid(state, x, y - 1, playerColour, pastPath)) {
            return true;
        }

        //Check straight up
        if (y < BOARD_Y - 1 && isCoordValid(state, x, y + 1, playerColour, pastPath)) {
            return true;
        }

        //Check to the left
        if (x > 0 && isCoordValid(state, x - 1, y, playerColour, pastPath)) {
            return true;
        }

        //Check to the right
        if (x < BOARD_X - 1 && isCoordValid(state, x + 1, y, playerColour, pastPath)) {
            return true;
        }

        //Check to the lower left
        if (x > 0 && y > 0 && isCoordValid(state, x - 1, y - 1, playerColour, pastPath)) {
            return true;
        }

        //Check to the lower right
        if (x < BOARD_X - 1 && y > 0 && isCoordValid(state, x + 1, y - 1, playerColour, pastPath)) {
            return true;
        }

        //Check to the upper right
        if (x < BOARD_X - 1 && y < BOARD_Y - 1 && isCoordValid(state, x + 1, y + 1, playerColour, pastPath)) {
            return true;
        }

        //Check to the upper left
        if (x > 0 && y < BOARD_Y - 1 && isCoordValid(state, x - 1, y + 1, playerColour, pastPath)) {
            return true;
        }

        //There is no hope here
        return false;
    }
}
