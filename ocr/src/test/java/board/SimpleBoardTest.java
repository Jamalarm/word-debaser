package board;

import org.junit.Assert;
import org.junit.Test;

public class SimpleBoardTest {

    @Test
    public void testCreateBoardFromString() {
        SimpleBoard board = new SimpleBoard("iowadwjandkwjndwakjdwakuhdwakjndwakjdnwakdnwakudwakawwadudnwakdnwakudwnakduwankduwankdawjndwakmdbwadbawkdkwajndwakjndakwjndkwajdaw");
        System.out.println(board);
        Assert.assertTrue(board.getChar(0, 0) == 'J');
        Assert.assertTrue(board.getChar(IBoard.BOARD_X - 1, 0) == 'W');
        Assert.assertTrue(board.getChar(0, IBoard.BOARD_Y - 1) == 'I');
        Assert.assertTrue(board.getChar(IBoard.BOARD_X - 1, IBoard.BOARD_Y - 1) == 'D');
    }

}