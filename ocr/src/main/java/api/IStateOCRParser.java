package api;

import state.IGameState;

import java.awt.*;

public interface IStateOCRParser {
    IGameState parseState(Image img);
}
