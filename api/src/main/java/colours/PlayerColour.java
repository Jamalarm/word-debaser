package colours;

import board.IBoard;

import java.awt.*;

public enum PlayerColour {
    BLUE("\u001B[44;37m", 0, new Color(2, 201, 232)),
    ORANGE("\u001B[41;37m", IBoard.BOARD_Y - 1, new Color(254, 137, 2)),
    BLACK("\u001B[40;37m", -1, new Color(2, 2, 2)),
    NONE("\u001B[0m", -1, new Color(255, 255, 255)),;

    private final String asciiFormatCode;
    private final int homeRow;
    private final Color color;

    PlayerColour(String asciiFormatCode, int homeRow, Color color) {
        this.asciiFormatCode = asciiFormatCode;
        this.homeRow = homeRow;
        this.color = color;
    }

    public static PlayerColour getDefaultColourForRow(int row) {
        for (PlayerColour playerColour : values()) {
            if (playerColour.homeRow == row) {
                return playerColour;
            }
        }

        return null;
    }

    public static PlayerColour getFromColourCode(Color color, int tolerance) {

        for (PlayerColour playerColour : values()) {
            if (coloursMatch(playerColour.color, color, tolerance)) {
                return playerColour;
            }
        }

        return NONE;
    }

    private static boolean coloursMatch(Color c1, Color c2, int tolerance) {

        //Normal Equals
        if (c1.equals(c2)) {
            return true;
        }

        //Fuzzy equals (return false if any component differences are more than tolerance)
        if (Math.abs(c1.getRed() - c2.getRed()) > tolerance) {
            return false;
        } else if (Math.abs(c1.getBlue() - c2.getBlue()) > tolerance) {
            return false;
        } else if (Math.abs(c1.getGreen() - c2.getGreen()) > tolerance) {
            return false;
        }

        return true;
    }

    public String getASCIIFormatCode() {
        return asciiFormatCode;
    }
}
