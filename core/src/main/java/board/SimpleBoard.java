package board;

import api.IBoard;
import exceptions.WordDebaserException;

import java.util.Arrays;

public class SimpleBoard implements IBoard {

    public static final int BOARD_X = 10;
    public static final int BOARD_Y = 13;
    public static final String SEPERATOR = " ";

    private char[][] chars;

    public SimpleBoard(char[][] chars) {
        this.chars = chars;
    }

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
                    int strindex = (y * BOARD_X) + x;
                    chars[x][y] = allChars.charAt(strindex);
                }
            }
        }
    }

    public char getChar(int x, int y) throws WordDebaserException {
        if (0 <= x && x < chars.length) {
            if (0 <= y && y < chars[x].length) {
                return chars[x][y];
            }
        }
        throw new WordDebaserException("Attempted to access bad index in word board");
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int y = 0; y < BOARD_Y; y++) {
            out.append(SEPERATOR);
            for (int x = 0; x < BOARD_X; x++) {
                out.append(chars[x][y]);
                out.append(SEPERATOR);
            }
            out.append("\n");
        }

        return out.toString();
    }
}
