package api;

import state.IState;

import java.awt.*;

public interface IStateOCRParser {
    IState parseState(Image img);
}
