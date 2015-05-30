package tile;

import java.util.List;

/**
 * Created by Tong on 30/05/2015.
 */
public class LetterTile {
    //coordinates
    private int x;
    private int y;
    //letter on the tile
    private char letter;
    //neighbouring tiles
    private LetterTile[] neighbours;
    //list of words that can start from this letter, represented as lists of tiles
    private List[] words;
    //minimal number of steps to win from this tile
    private int stepsToWin;

    public void setLetter(char letter){
        this.letter = letter;
    }

    public char getLetter(){
        return this.letter;
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setNeighbours( LetterTile[] tiles ){
        this.neighbours = tiles;
    }

    public LetterTile[] getNeighbours(){
        return this.neighbours;
    }

}
