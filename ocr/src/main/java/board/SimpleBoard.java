package board;

public class SimpleBoard implements IBoard {

    public static final String SEPARATOR = " ";

    private char[][] chars;

    public SimpleBoard(char[][] chars) {
        this.chars = chars;
    }

    /**
     * Builds a board object using a continuous string, filling from the top left in rows
     * @param allChars Unbroken string of length BOARD_X * BOARD_Y
     */
    public SimpleBoard(String allChars) {
        allChars = allChars.toUpperCase();
        if (allChars.length() == BOARD_X * BOARD_Y) {
            //Initialise arrays
            chars = new char[BOARD_X][];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = new char[BOARD_Y];
            }
            for (int y = 0; y < BOARD_Y; y++) {
                for (int x = 0; x < BOARD_X; x++) {
                    int strIndex = (y * BOARD_X) + x;
                    chars[x][y] = allChars.charAt(strIndex);
                }
            }
        }
    }

    public char getChar(int x, int y) {
        if (0 <= x && x < chars.length) {
            if (0 <= y && y < chars[x].length) {
                return chars[x][y];
            }
        }
        throw new IndexOutOfBoundsException(
                String.format("Index requested (%d, %d) is outside of the Game Board", x, y));
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int y = 0; y < BOARD_Y; y++) {
            out.append(SEPARATOR);
            for (int x = 0; x < BOARD_X; x++) {
                out.append(chars[x][y]);
                out.append(SEPARATOR);
            }
            out.append("\n");
        }

        return out.toString();
    }
}
