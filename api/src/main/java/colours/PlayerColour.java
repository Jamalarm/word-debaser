package colours;

import board.IBoard;

public enum PlayerColour {
    BLUE("\u001B[44;37m", 0, -16777216),
    ORANGE("\u001B[41;37m", IBoard.BOARD_Y - 1, -221361),
    BLACK("\u001B[40;37m", -1, -96256),
    NONE("\u001B[0m", -1, -1);

    private final String asciiFormatCode;
    private final int homeRow;
    private final int colourCode;

    PlayerColour(String asciiFormatCode, int homeRow, int colourCode) {
        this.asciiFormatCode = asciiFormatCode;
        this.homeRow = homeRow;
        this.colourCode = colourCode;
    }

    public static PlayerColour getDefaultColourForRow(int row) {
        for (PlayerColour playerColour : values()) {
            if (playerColour.homeRow == row) {
                return playerColour;
            }
        }

        return null;
    }

    public static PlayerColour getFromColourCode(int colourCode) {
        for (PlayerColour playerColour : values()) {
            if (playerColour.colourCode == colourCode) {
                return playerColour;
            }
        }

        return null;
    }

    public String getASCIIFormatCode() {
        return asciiFormatCode;
    }
}
