package parser;

import api.IPlayerColourParser;
import colours.PlayerColour;

import java.awt.image.BufferedImage;

public class RandomSamplingMedianPlayerColourParser implements IPlayerColourParser {

    public static final int SAMPLE_COUNT = 10;
    public static final int BORDER_BIAS = 5;

    public PlayerColour[][] parsePlayerColours(BufferedImage[][] cellImages) {
        return new PlayerColour[0][];
    }
}
