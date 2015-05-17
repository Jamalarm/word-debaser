package api;

import state.IState;

import java.awt.*;

public interface IStateOCRParser {

    /**
     * Uses some OCR technique to produce a state object from an input image of the game
     * @param img A screenshot of the game screen
     * @return A IState representing the state of the game as in the image supplied
     */
    IState parseState(Image img);
}
