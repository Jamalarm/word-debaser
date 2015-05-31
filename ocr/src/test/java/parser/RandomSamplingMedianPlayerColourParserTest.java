package parser;

import api.IPlayerColourParser;
import colours.PlayerColour;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Arrays;

import static colours.PlayerColour.*;
import static org.junit.Assert.assertTrue;

public class RandomSamplingMedianPlayerColourParserTest {

    @Test
    public void testParsePlayerColours() throws Exception {
        IPlayerColourParser parser = new RandomSamplingMedianPlayerColourParser();

        ImagePartitioner imgPart = new ImagePartitioner(ImageIO.read(new File("src/test/resources/Screenshot_2015-05-16-23-24-52.png")));

        PlayerColour[][] colours = parser.parsePlayerColours(imgPart.getCellImages());

        PlayerColour[][] expectedColours = new PlayerColour[][]{
                {BLUE, NONE, NONE, NONE, NONE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE, NONE, NONE, ORANGE},
                {BLUE, BLUE, BLUE, NONE, NONE, NONE, ORANGE, ORANGE, ORANGE, ORANGE, NONE, BLACK, ORANGE},
                {BLUE, BLUE, NONE, NONE, NONE, NONE, ORANGE, NONE, NONE, NONE, ORANGE, ORANGE, ORANGE},
                {BLUE, NONE, NONE, ORANGE, ORANGE, ORANGE, ORANGE, NONE, ORANGE, ORANGE, NONE, NONE, ORANGE},
                {BLUE, NONE, BLUE, NONE, ORANGE, ORANGE, ORANGE, ORANGE, NONE, ORANGE, NONE, NONE, ORANGE},
                {BLUE, NONE, BLUE, BLUE, BLUE, BLUE, NONE, NONE, BLACK, NONE, ORANGE, ORANGE, ORANGE},
                {BLUE, NONE, NONE, BLUE, BLUE, NONE, NONE, NONE, NONE, ORANGE, NONE, NONE, ORANGE},
                {BLUE, NONE, BLUE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, ORANGE},
                {BLUE, BLUE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE, ORANGE},
                {BLUE, NONE, NONE, NONE, NONE, BLACK, NONE, NONE, NONE, NONE, NONE, NONE, ORANGE}
        };
        assertTrue(Arrays.deepEquals(colours, expectedColours));
    }
}