package parser;

import java.awt.image.BufferedImage;

import static board.IBoard.BOARD_X;
import static board.IBoard.BOARD_Y;

public class ImagePartitioner {

    public static final double REF_IMG_X = 1080;
    public static final double REF_IMG_Y = 1920;

    public static final double TOP_OF_BOARD = 504 / REF_IMG_Y;
    public static final double BOTTOM_OF_BOARD = 1764 / REF_IMG_Y;
    public static final double LEFT_OF_BOARD = 55 / REF_IMG_X;
    public static final double RIGHT_OF_BOARD = 1025 / REF_IMG_X;

    private final BufferedImage img;
    private final int imgX;
    private final int imgY;

    public ImagePartitioner(BufferedImage img) {
        this.img = img;
        this.imgX = img.getWidth();
        this.imgY = img.getHeight();
    }

    public BufferedImage getBoard() {
        int[] topLeft = getTopLeft();
        int[] boardSize = getBoardSize();
        return img.getSubimage(topLeft[0], topLeft[1], boardSize[0], boardSize[1]);
    }

    public BufferedImage[][] getCellImages() {

        BufferedImage board = getBoard();

        int cellSizeX = board.getWidth() / BOARD_X;
        int cellSizeY = board.getHeight() / BOARD_Y;

        //Array storage of subcells flips coord system, images are indexed from top left and our board
        //implementation is indexed from the bottom left

        BufferedImage[][] cellImages = new BufferedImage[BOARD_X][];
        for (int x = 0; x < BOARD_X; x++) {
            int boardXOrigin = (int) (((double) x / BOARD_X) * board.getWidth());
            cellImages[x] = new BufferedImage[BOARD_Y];

            for (int y = 0; y < BOARD_Y; y++) {
                int boardYOrigin = (int) (((double) y / BOARD_Y) * board.getHeight());

                BufferedImage cellImage = board.getSubimage(boardXOrigin, boardYOrigin, cellSizeX, cellSizeY);
                cellImages[x][BOARD_Y - y - 1] = cellImage;
            }
        }

        return cellImages;
    }

    private int[] getTopLeft() {
        return new int[]{
                (int) (LEFT_OF_BOARD * this.imgX),
                (int) (TOP_OF_BOARD * this.imgY)
        };
    }

    private int[] getBoardSize() {
        final int[] topLeft = getTopLeft();
        final int[] bottomRight = new int[]{
                (int) (RIGHT_OF_BOARD * this.imgX),
                (int) (BOTTOM_OF_BOARD * this.imgY)
        };

        return new int[]{
                bottomRight[0] - topLeft[0],
                bottomRight[1] - topLeft[1]
        };
    }
}
