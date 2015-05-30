package board;

import tile.LetterTile;

import java.util.Arrays;
import static java.lang.Math.*;
/**
 * Created by Tong on 30/05/2015.
 */
public class TileBoard implements IBoard {
    public static final String SEPARATOR = " \u001B[0m";
    private LetterTile[][] tiles;

    public TileBoard(LetterTile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * Builds a board object using a continuous string, filling from the top left in rows
     *
     * @param allChars Unbroken string of length BOARD_X * BOARD_Y
     */
    public TileBoard(String allChars) {
        allChars = allChars.toUpperCase();
        if (allChars.length() == BOARD_X * BOARD_Y) {
            //Initialise arrays
            tiles = new LetterTile[BOARD_X][];
            for (int i = 0; i < tiles.length; i++) {
                tiles[i] = new LetterTile[BOARD_Y];
            }
            for (int y = 0; y < BOARD_Y; y++) {
                for (int x = 0; x < BOARD_X; x++) {
                    int strIndex = ((BOARD_Y - y - 1) * BOARD_X) + x;
                    LetterTile t = new LetterTile();
                    t.setLetter(allChars.charAt(strIndex));
                    t.setCoordinates(x, y);
                    tiles[x][y] = t;
                }
            }
            ;
            //set neighbours on each tile
            for (int y = 0; y < BOARD_Y; y++) {
                for (int x = 0; x < BOARD_X; x++) {
                    LetterTile centre = tiles[x][y];
                    int xmin = max(0, x - 1);
                    int xmax = min(BOARD_X-1, x + 1);
                    int ymin = max(0, y - 1);
                    int ymax = min(BOARD_Y-1, y + 1);
                    int neighbouringRange = (ymax - ymin + 1) * (xmax - xmin + 1) - 1;
                    LetterTile[] neighbours = new LetterTile[neighbouringRange];
                    int idx = 0;
                    for (int j = ymin; j <= ymax; j++) {
                        for (int i = xmin; i <= xmax; i++) {
                            if (i != x || j!= y) {
                                neighbours[idx++] = tiles[i][j];
                            }
                        }
                    }
                    centre.setNeighbours(neighbours);
                }
            }

        } else {
            throw new IllegalArgumentException(String.format("Supplied string must be exactly %d characters", BOARD_X * BOARD_Y));
        }
    }

    public char getChar(int x, int y) {
        if (0 <= x && x < tiles.length) {
            if (0 <= y && y < tiles[x].length) {
                return tiles[x][y].getLetter();
            }
        }
        throw new IndexOutOfBoundsException(
                String.format("Index requested (%d, %d) is outside of the Game Board", x, y));
    }

    public LetterTile getTile(int x, int y){
        return this.tiles[x][y];
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int y = BOARD_Y - 1; y >= 0; y--) {
            out.append(SEPARATOR);
            for (int x = 0; x < BOARD_X; x++) {
                out.append(tiles[x][y].getLetter());
                out.append(SEPARATOR);
            }
            out.append("\n");
        }

        return out.toString();
    }
}