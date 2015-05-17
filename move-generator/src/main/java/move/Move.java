package move;

import colours.PlayerColour;

import java.util.List;

public class Move {

    private final String word;
    private final PlayerColour colour;
    private List<int[]> path;

    public Move(String word, PlayerColour colour, List<int[]> path) {
        this.word = word;
        this.colour = colour;
        this.path = path;
    }

    /**
     * The word that this move is playing
     * @return A String representing the word
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @return The list of coordinates taken to spell the word of this move
     */
    public List<int[]> getPath() {
        return path;
    }

    /**
     *
     * @return The player colour making this move
     */
    public PlayerColour getColour() {
        return colour;
    }

    /**
     * Check if the supplied coordinate is inside the path of this move
     * @param x coord
     * @param y coord
     * @return true if the supplied coord is in the path of this move object
     */
    public boolean isInPath(int x, int y) {
        for (int[] coords : path) {
            if (x == coords[0] && y == coords[1]) {
                return true;
            }
        }

        return false;
    }
}
