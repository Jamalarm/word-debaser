package state;

import board.IBoard;
import colours.PlayerColour;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static board.IBoard.BOARD_X;
import static board.IBoard.BOARD_Y;
import static org.mockito.Mockito.*;

public class SimpleStateTest {

    @Test
    public void testCreateState() {
        SimpleState state = createMockedState();
        Assert.assertTrue(state.getPlayerColour(0, 0).equals(PlayerColour.ORANGE));
        Assert.assertTrue(state.getPlayerColour(BOARD_X - 1, BOARD_Y - 1).equals(PlayerColour.BLUE));

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

        Arrays.fill(colours[0], PlayerColour.ORANGE);
        Arrays.fill(colours[BOARD_X - 1], PlayerColour.BLUE);

        return new SimpleState(mockedBoard, colours);
    }

    @Test
    public void testStateBuilder() {
        SimpleState state = createMockedState();
        Assert.assertTrue(state.getPlayerColour(0, 0).equals(PlayerColour.ORANGE));
        Assert.assertTrue(state.getPlayerColour(BOARD_X - 1, BOARD_Y - 1).equals(PlayerColour.BLUE));

        StateBuilder builder = state.toBuilder();

        builder.setColour(PlayerColour.BLUE, 0, 0);
        builder.setColour(PlayerColour.ORANGE, BOARD_X - 1, BOARD_Y - 1);

        IState newState = builder.build();

        //check original state has not changed
        Assert.assertTrue(state.getPlayerColour(0, 0).equals(PlayerColour.ORANGE));
        Assert.assertTrue(state.getPlayerColour(BOARD_X - 1, BOARD_Y - 1).equals(PlayerColour.BLUE));

        //check new state has changed
        Assert.assertTrue(newState.getPlayerColour(0, 0).equals(PlayerColour.BLUE));
        Assert.assertTrue(newState.getPlayerColour(BOARD_X - 1, BOARD_Y - 1).equals(PlayerColour.ORANGE));


    }

}