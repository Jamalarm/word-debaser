package move;

import java.util.List;

public class Move {

    private final String word;
    private List<int[]> path;

    public Move(String word, List<int[]> path) {
        this.word = word;
        this.path = path;
    }

    public String getWord() {
        return word;
    }

    public List<int[]> getPath() {
        return path;
    }
}
