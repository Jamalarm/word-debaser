package state;

import board.SimpleBoard;
import colours.PlayerColour;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Arrays;

import static board.IBoard.BOARD_X;
import static board.IBoard.BOARD_Y;
import static colours.PlayerColour.*;

public class StateGenerationUtil {

    public static IState generateRandomState(double fillQuotient) {
        //TODO Make this use a more reasonable distribution of letters - more 'e's and so on
        SimpleBoard simpleBoard = new SimpleBoard(RandomStringUtils.randomAlphabetic(130));

        PlayerColour[][] colours = new PlayerColour[BOARD_X][];

        for (int i = 0; i < BOARD_X; i++) {
            colours[i] = new PlayerColour[BOARD_Y];
            Arrays.fill(colours[i], NONE);
            colours[i][0] = BLUE;
            colours[i][BOARD_Y - 1] = ORANGE;
        }

        //Fill Blue
        for (int y = 1; y < BOARD_Y - 1; y++) {
            for (int x = 0; x < BOARD_X; x++) {
                if ((x == 0 && (colours[x][y - 1] == BLUE || colours[x + 1][y - 1] == BLUE)) ||
                        (x == BOARD_X - 1 && (colours[x - 1][y - 1] == BLUE || colours[x][y - 1] == BLUE)) ||
                        (x > 0 && x < BOARD_X - 1 && (colours[x - 1][y - 1] == BLUE || colours[x][y - 1] == BLUE || colours[x + 1][y - 1] == BLUE))) {
                    colours[x][y] = Math.random() < fillQuotient - (y * 0.05)? BLUE : NONE;
                }
            }
        }

        //Fill Orange
        for (int y = BOARD_Y - 2; y >= 1; y--) {
            for (int x = 0; x < BOARD_X; x++) {
                if ((x == 0 && (colours[x][y + 1] == ORANGE || colours[x + 1][y + 1] == ORANGE)) ||
                        (x == BOARD_X - 1 && (colours[x - 1][y + 1] == ORANGE || colours[x][y + 1] == ORANGE)) ||
                        (x > 0 && x < BOARD_X - 1 && (colours[x - 1][y + 1] == ORANGE || colours[x][y + 1] == ORANGE || colours[x + 1][y + 1] == ORANGE))) {
                    colours[x][y] = Math.random() < fillQuotient - ((BOARD_Y - y) * 0.05)? ORANGE : NONE;
                }
            }
        }


        SimpleState state = new SimpleState(simpleBoard, colours);

        System.out.println(state);

        return null;
    }

}
