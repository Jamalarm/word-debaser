package board;

public interface IBoard {

    // Master values for allowable board size
    int BOARD_X = 10;
    int BOARD_Y = 13;

    /**
     * Gets the character at the specified index on the game board.
     * Throws IndexOutOfBoundsException
     * @param x index between 0 and BOARD_X
     * @param y index between 0 and BOARD_Y
     * @return character at the specified index
     */
    char getChar(int x, int y);
}
