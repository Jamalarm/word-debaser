package parser;

import org.junit.Test;

import java.io.File;

public class TesseractOCRParserTest {

    @Test
    public void testParseState() throws Exception {
        TesseractOCRParser ocrParser = new TesseractOCRParser();

        ocrParser.parseState(new File("src/test/resources/Screenshot_2015-05-16-23-24-52.png"));

    }
}