package api;

import colours.PlayerColour;

import java.awt.image.BufferedImage;

public interface IPlayerColourParser {

    PlayerColour[][] parsePlayerColours(BufferedImage[][] cellImages);
}
