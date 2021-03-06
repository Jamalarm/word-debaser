package parser;

import api.IPlayerColourParser;
import api.IStateOCRParser;
import board.IBoard;
import board.SimpleBoard;
import colours.PlayerColour;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import state.IState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class TesseractOCRParser implements IStateOCRParser {

    private static final String TESS_DATA = "tessData";

    private final Tesseract1 tess;
    private final IPlayerColourParser colourParser;

    public TesseractOCRParser() {
        //Get tesseract insance
        tess = getTessInstance();
        colourParser = new RandomSamplingMedianPlayerColourParser();
    }

    public IState parseState(File img) {

        //Load image into memory
        try {

            ImagePartitioner imagePartitioner = new ImagePartitioner(ImageIO.read(img));

            BufferedImage[][] cellImages = imagePartitioner.getCellImages();

            PlayerColour[][] playerColours = colourParser.parsePlayerColours(cellImages);

            IBoard board = buildBoardFromTotalParsing(imagePartitioner.getBoard());
//            IBoard board = buildBoardFromSingleParsings(cellImages);
            System.out.println(Arrays.deepToString(playerColours));

        } catch (IOException e) {
            System.err.println("Error encountered in loading image file for parsing");
            e.printStackTrace();
        }
        return null;
    }

    private IBoard buildBoardFromTotalParsing(BufferedImage board) {

        tess.setPageSegMode(6);

        try {
            String s = tess.doOCR(board);
            System.out.println(s);
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        return null;
    }

    private IBoard buildBoardFromSingleParsings(BufferedImage[][] cellImages) {
        char[][] chars = new char[cellImages.length][];

        for (int x = 0; x < chars.length; x++) {
            chars[x] = new char[cellImages[x].length];

            for (int y = 0; y < chars[x].length; y++) {
                chars[x][y] = getSingleChar(cellImages[x][y]);
            }
        }


        return new SimpleBoard(chars);
    }

    private char getSingleChar(BufferedImage cellImage) {

        try {
            String ocr = tess.doOCR(cellImage);

            if (ocr.length() < 1) {
                throw new TesseractException(String.format("OCR string \"%s\" must be one character in length exactly", ocr));
            }

            if (ocr.charAt(0) == '?') {
                throw new TesseractException("Can't read char");
            }

            return ocr.charAt(0);

        } catch (TesseractException e) {
            e.printStackTrace();
            ImageDisplayUtil.displayImagePopup(cellImage);
            return '-';
        }
    }

    private Tesseract1 getTessInstance() {
        Tesseract1 tess = new Tesseract1();
        File tessDataFolder = LoadLibs.extractTessResources(TESS_DATA);
        tess.setDatapath(tessDataFolder.getParentFile().getAbsolutePath());
        tess.setConfigs(Collections.singletonList("upper_case_alphabet_only"));
//        tess.setPageSegMode(10);
        return tess;
    }
}
