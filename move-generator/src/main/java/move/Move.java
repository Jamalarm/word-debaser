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

    public String getWord() {
        return word;
    }

    public List<int[]> getPath() {
        return path;
    }

    public PlayerColour getColour() {
        return colour;
    }

    public boolean isInPath(int x, int y) {
        for (int[] coords : path) {
            if (x == coords[0] && y == coords[1]) {
                return true;
            }
        }

        return false;
    }
}
