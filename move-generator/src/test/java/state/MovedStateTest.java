package state;

import board.IBoard;
import colours.PlayerColour;
import move.Move;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static board.IBoard.BOARD_X;
import static board.IBoard.BOARD_Y;
import static colours.PlayerColour.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovedStateTest {

    @Test
    public void testCreateAndVerify() {
        SimpleState mockedState = createMockedState();

        List<int[]> path = new ArrayList<int[]>();
        path.add(new int[]{6, 0});
        path.add(new int[]{6, 1});
        path.add(new int[]{6, 2});
        path.add(new int[]{6, 3});
        path.add(new int[]{7, 4});
        path.add(new int[]{7, 5});

        Move move = new Move("IAMACAKE", BLUE, path);

        MovedState movedState = new MovedState(mockedState, move);

        Assert.assertTrue(movedState.getPlayerColour(4, 4).equals(NONE));
        Assert.assertTrue(movedState.getPlayerColour(3, 3).equals(NONE));
        Assert.assertTrue(movedState.getPlayerColour(7, 5).equals(BLUE));

    }

    private SimpleState createMockedState() {
        //Mock a board object
        IBoard mockedBoard = mock(IBoard.class);
        when(mockedBoard.getChar(anyInt(), anyInt())).thenReturn('T');

        PlayerColour[][] colours = new PlayerColour[BOARD_X][];

        for (int i = 0; i < colours.length; i++) {
            colours[i] = new PlayerColour[BOARD_Y];
            Arrays.fill(colours[i], PlayerColour.NONE);
        }

        for (int i = 0; i < BOARD_X; i++) {
            colours[i] = new PlayerColour[BOARD_Y];
            Arrays.fill(colours[i], NONE);
            colours[i][0] = BLUE;
            colours[i][BOARD_Y - 1] = ORANGE;
        }

        colours[4][4] = BLUE;
        colours[3][4] = BLUE;
        colours[3][3] = BLUE;
        colours[4][3] = BLUE;

        return new SimpleState(mockedBoard, colours);
    }
}