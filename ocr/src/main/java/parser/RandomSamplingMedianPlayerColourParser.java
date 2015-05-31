package parser;

import api.IPlayerColourParser;
import colours.PlayerColour;
import org.apache.commons.lang.math.RandomUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class RandomSamplingMedianPlayerColourParser implements IPlayerColourParser {

    public static final int SAMPLE_COUNT = 50;
    public static final int BORDER_BIAS = 5;



    public PlayerColour[][] parsePlayerColours(BufferedImage[][] cellImages) {
        PlayerColour[][] colours = new PlayerColour[cellImages.length][];

        for (int x = 0; x < colours.length; x++) {
            colours[x] = new PlayerColour[cellImages[x].length];

            for (int y = 0; y < colours[x].length; y++) {

                //check if we're in a home row
                PlayerColour defaultColour = PlayerColour.getDefaultColourForRow(y);

                if (defaultColour != null) {
                    colours[x][y] = defaultColour;
                } else {
                    colours[x][y] = getCellColour(cellImages[x][y]);
                }

            }
        }

        return colours;
    }

    private PlayerColour getCellColour(BufferedImage cellImg) {

        //Calculate ranges
        int xRange = cellImg.getWidth() - BORDER_BIAS;
        int yRange = cellImg.getHeight() - BORDER_BIAS;

        //TODO rewrite without autoboxing - maybe use Tcollections int int map?
        Map<Integer, Integer> colourCounts = new HashMap<Integer, Integer>();

        for (int i = 0; i < SAMPLE_COUNT; i++) {
            int x = RandomUtils.nextInt(xRange) + BORDER_BIAS;
            int y = RandomUtils.nextInt(yRange) + BORDER_BIAS;
            Integer rgb = cellImg.getRGB(x, y);
            if (colourCounts.containsKey(rgb)) {
                Integer current = colourCounts.get(rgb);
                colourCounts.put(rgb, current + 1);
            } else {
                colourCounts.put(rgb, 1);
            }
        }

        //Select median value
        Integer medianColour = null;
        for (Integer colour : colourCounts.keySet()) {
            if (medianColour == null) {
                medianColour = colour;
            } else if (colourCounts.get(colour) > colourCounts.get(medianColour)) {
                medianColour = colour;
            }
        }

        return PlayerColour.getFromColourCode(new Color(medianColour), 5);
    }
}
