package state;

import board.IBoard;
import colours.PlayerColour;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class SimpleStateTest {

    @Test
    public void testCreateState() {
        //Mock a board object
        IBoard mockedBoard = mock(IBoard.class);
        when(mockedBoard.getChar(anyInt(), anyInt())).thenReturn('T');

        PlayerColour[][] colours = new PlayerColour[IBoard.BOARD_X][];

        for (int i = 0; i < colours.length; i++) {
            colours[i] = new PlayerColour[IBoard.BOARD_Y];
            Arrays.fill(colours[i], PlayerColour.NONE);
        }

        Arrays.fill(colours[0], PlayerColour.ORANGE);
        Arrays.fill(colours[IBoard.BOARD_X - 1], PlayerColour.BLUE);

        SimpleState state = new SimpleState(mockedBoard, colours);

        System.out.println(state);

    }

}