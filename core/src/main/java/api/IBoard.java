package api;

import exceptions.WordDebaserException;

public interface IBoard {
    char getChar(int x, int y) throws WordDebaserException;
}
