package board;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tong on 30/05/2015.
 */
public class TileBoardTest {
    @Test
    public void testCreateBoardFromString() {
        TileBoard board = new TileBoard("iowadwjandkwjndwakjdwakuhdwakjndwakjdnwakdnwakudwakawwadudnwakdnwakudwnakduwankduwankdawjndwakmdbwadbawkdkwajndwakjndakwjndkwajdaw");
        System.out.println(board);
        Assert.assertTrue(board.getChar(0, 0) == 'J');
        Assert.assertTrue(board.getChar(IBoard.BOARD_X - 1, 0) == 'W');
        Assert.assertTrue(board.getChar(0, IBoard.BOARD_Y - 1) == 'I');
        Assert.assertTrue(board.getChar(IBoard.BOARD_X - 1, IBoard.BOARD_Y - 1) == 'D');
        Assert.assertEquals(board.getTile(0, 0).getNeighbours().length, 3);
        Assert.assertEquals(board.getTile(0, 1).getNeighbours().length, 5);
        Assert.assertEquals(board.getTile(1, 1).getNeighbours().length, 8);
    }

}
